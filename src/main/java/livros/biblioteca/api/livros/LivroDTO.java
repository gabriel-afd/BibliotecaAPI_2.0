package livros.biblioteca.api.livros;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import livros.biblioteca.api.autor.DadosAutor;


@Embeddable
public record LivroDTO(

        @NotBlank
        String title,
        @NotBlank
        String isbn,
        @NotNull @Valid
        DadosAutor autor ,
        @NotNull @Valid
        DadosPublicacao publication,
        @Enumerated(EnumType.STRING)
        @NotNull
        Categoria category,
        @NotBlank
        String language


){}

