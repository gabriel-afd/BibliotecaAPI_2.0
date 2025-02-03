package livros.biblioteca.api.domain.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        @NotNull
        @Future
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm[:ss]")
        LocalDateTime data,
        Long qtdDias,
        Categoria category

) {
        public DadosReservaLivro(ReservaLivro reserva) {
                this(reserva.getId(), reserva.getLivro().getId(), reserva.getLeitor().getId(),reserva.getData(),reserva.getQtdDias(),reserva.getLivro().getCategory());
        }
}
