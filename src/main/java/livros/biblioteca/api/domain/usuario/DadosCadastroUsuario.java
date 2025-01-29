package livros.biblioteca.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotNull
        Long id,
        @NotBlank
        String login

) {
}
