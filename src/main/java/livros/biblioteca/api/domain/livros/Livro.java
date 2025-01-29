package livros.biblioteca.api.domain.livros;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import livros.biblioteca.api.domain.autor.Autor;
import livros.biblioteca.api.leitor.DadosCadastroLeitor;
import lombok.*;


@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor //Gera um construtor que recebe como parametro todos os atributos da classe
@EqualsAndHashCode(of = "id") //A comparação de igualdade entre dois objetos Livro deve ser feita somente pelo id

public class Livro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private String language;

    @Embedded
    private Autor autor;

    @Embedded
    private DadosPublicacao publication;

    @Enumerated(EnumType.STRING)
    private Categoria category;

    private Boolean ativo;


    public Livro(LivroDTO dados) {
        this.ativo = true;
        this.title = dados.title();
        this.isbn = dados.isbn();
        this.language = dados.language();
        this.autor = new Autor(dados.autor());
        this.publication = dados.publication();
        this.category = dados.category();
        this.language = dados.language();
    }

    public void atualizarLivros(@Valid LivroAtualizarDTO dados) {
        if(dados.title() != null){
            this.title = dados.title();
        }
        if(dados.autor() != null){
            this.autor.atualizarAutor(dados.autor());
        }
        if(dados.autor() != null){
            this.category = dados.category();
        }
    }

    public void excluirLivroos() {
        this.ativo = false;
    }


}
