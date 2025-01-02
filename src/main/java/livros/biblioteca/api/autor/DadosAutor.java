package livros.biblioteca.api.autor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @NotBlank
        String name,
        @NotBlank
        String nationality

) {

}
