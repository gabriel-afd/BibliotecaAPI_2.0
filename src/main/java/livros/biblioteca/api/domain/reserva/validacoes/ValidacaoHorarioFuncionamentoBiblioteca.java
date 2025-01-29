package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioFuncionamentoBiblioteca implements ValidadorReservas{

    public void validar(DadosReservaLivro dadosReservaLivro){
        var dataReserva = dadosReservaLivro.data();
        var domingo = dataReserva.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var comecoExpediente = dataReserva.getHour() < 8;
        var horaAlmoco = dataReserva.getHour() >= 12 && dataReserva.getHour() <= 14;
        var fimExpediente = dataReserva.getHour() > 18;

        if (domingo || comecoExpediente || horaAlmoco || fimExpediente){
            throw new ValidacaoException("Bilioteca fora do horário de funcionamento");
        }
    }
}
