package livros.biblioteca.api.domain.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface ReservaRepository extends JpaRepository<ReservaLivro,Long> {
    Boolean existsByLivroIdAndData(Long idLivro, @Future LocalDateTime data);



    Long countByLeitorIdAndDataAfter(@NotNull Long leitor_Id, LocalDateTime dataAtual);
}
