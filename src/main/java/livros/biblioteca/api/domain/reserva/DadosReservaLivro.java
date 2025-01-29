package livros.biblioteca.api.domain.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import livros.biblioteca.api.domain.livros.Categoria;

import java.time.LocalDateTime;

public record DadosReservaLivro(

        Long id,
        @NotNull
        Long idLivro,
        @NotNull
        Long idLeitor,
        @NotBlank
        String title,
        @NotNull
        @Future
        LocalDateTime data,
        Long qtdDias,
        Categoria category

) {
        public DadosReservaLivro(ReservaLivro reserva) {
                this(reserva.getId(), reserva.getLivro().getId(), reserva.getLeitor().getId(),reserva.getLivro().getTitle(),reserva.getData(),reserva.getQtdDias(),reserva.getLivro().getCategory());
        }
}
