package livros.biblioteca.api.domain.livros;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import livros.biblioteca.api.domain.autor.DadosAutor;


@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
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

