package livros.biblioteca.api.domain.livros;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import livros.biblioteca.api.domain.autor.DadosAutor;
import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.reserva.ReservaLivro;
import livros.biblioteca.api.leitor.DadosCadastroLeitor;
import livros.biblioteca.api.leitor.Leitor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Forçar cenário em que nenhum livro esteja disponivel, garantir lógica de disponibilidade. Se há livros cadastrados na categoria e data especificadas, logo o retorno será null")
    void escolhaAleatoriaDeLivroMesmaCategoriaCenario1() {
        var proxSexta10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .atTime(10,0);

        var livro = cadastrarLivro("Livro","123456789", dadosAutor(),dadosPublicacao(),Categoria.AVENTURA,"PT-BR");
        var leitor = cadastrarLeitor(1L,"ABC","ABC@email");
        cadastrarReserva(livro,leitor,proxSexta10,5L);

        var livroLivre = livroRepository.escolhaAleatoriaDeLivroMesmaCategoria(Categoria.AVENTURA,proxSexta10);

        assertThat(livroLivre).isNull();
    }

    //Para popular o banco de dados de teste

    private void cadastrarReserva(Livro livro, Leitor leitor, LocalDateTime data,Long qtdDias){
        entityManager.persist(new ReservaLivro(null,livro,leitor,data,qtdDias));
    }


    private Leitor cadastrarLeitor(Long id,String nome, String email){
        var leitor = new Leitor(dadosLeitor(id,nome,email));
        entityManager.persist(leitor);
        return leitor;
    }

    private Livro cadastrarLivro(String title, String isbn, DadosAutor autor, DadosPublicacao publication, Categoria category, String language){
        var livro = new Livro(dadosLivro(title, isbn, autor, publication, category, language));
        entityManager.persist(livro);
        return livro;
    }

    private LivroDTO dadosLivro(String title, String isbn, DadosAutor autor, DadosPublicacao publication, Categoria category, String language){
        return new LivroDTO(
                title,
                isbn,
                autor,
                publication,
                category,
                language
        );
    }

    private DadosCadastroLeitor dadosLeitor(Long id,String nome, String email){
        return new DadosCadastroLeitor(id,nome,email);
    }

    private DadosAutor dadosAutor(){
        return new DadosAutor(
                "ABC",
                "Brasil"

        );
    }

   private DadosPublicacao dadosPublicacao(){
        return new DadosPublicacao(
                2024,
                "Brasil"
        );

   }

}