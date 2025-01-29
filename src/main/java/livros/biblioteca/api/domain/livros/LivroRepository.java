package livros.biblioteca.api.domain.livros;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    Page<Livro> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select l from Livro l
            where
            l.ativo = true
            and
            l.category = :categoria
            and
            l.id not in(
                select r.livro.id from ReservaLivro r
                where
                r.data = :data
            )
            order by rand()
            limit 1
            """)
    Livro escolhaAleatoriaDeLivroMesmaCategoria(Categoria categoria, @NotNull @Future LocalDateTime data);

    @Query("""
            select l.ativo from Livro l
            where
            l.id = :id
            """)
    boolean findAtivoById(Long id);
}
