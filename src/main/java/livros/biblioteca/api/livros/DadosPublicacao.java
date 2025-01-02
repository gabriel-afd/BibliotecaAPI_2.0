package livros.biblioteca.api.livros;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPublicacao(
        @NotNull
        Integer year,
        @NotBlank
        String location
) {}
