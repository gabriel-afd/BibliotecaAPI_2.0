package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ValidacaoReservaMaximo7Dias implements ValidadorReservas{

    public void validar(DadosReservaLivro dadosReservaLivro){
        LocalDate dataAtual = LocalDate.now();
        var dataReserva = dadosReservaLivro.data().toLocalDate();

        long diferencaDias = ChronoUnit.DAYS.between(dataAtual,dataReserva);

        if (diferencaDias < 0){
            throw new ValidacaoException("A data de reserva não pode ser no passado");
        }

        if(diferencaDias>=7){
            throw new ValidacaoException("A reserva do livro pode ser feita por, no máximo, 7 dias");
        }
    }
}
