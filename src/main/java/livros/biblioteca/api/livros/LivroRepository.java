package livros.biblioteca.api.livros;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    Page<Livro> findAllByAtivoTrue(Pageable paginacao);
}
