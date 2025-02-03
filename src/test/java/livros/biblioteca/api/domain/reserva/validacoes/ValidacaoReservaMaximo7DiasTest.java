package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.livros.Categoria;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidacaoReservaMaximo7DiasTest {

    private ValidacaoReservaMaximo7Dias validador;

    @BeforeEach
    void setUp() {
        validador = new ValidacaoReservaMaximo7Dias();
    }

    private DadosReservaLivro criarDadosReserva(LocalDateTime data) {
        return new DadosReservaLivro(1L, 1L, 1L, data, 7L, Categoria.AVENTURA);
    }

    @Test
    void deveLancarExcecao_QuandoDataReservaForNoPassado() {
        LocalDateTime dataPassada = LocalDateTime.now().minusDays(1);
        DadosReservaLivro dados = criarDadosReserva(dataPassada);
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoDataReservaForExatamente7DiasNoFuturo() {
        LocalDateTime dataLimite = LocalDateTime.now().plusDays(7);
        DadosReservaLivro dados = criarDadosReserva(dataLimite);
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoDataReservaForMaisDe7DiasNoFuturo() {
        LocalDateTime dataFutura = LocalDateTime.now().plusDays(8);
        DadosReservaLivro dados = criarDadosReserva(dataFutura);
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void naoDeveLancarExcecao_QuandoDataReservaForHoje() {
        LocalDateTime dataHoje = LocalDateTime.now();
        DadosReservaLivro dados = criarDadosReserva(dataHoje);
        assertDoesNotThrow(() -> validador.validar(dados));
    }

    @Test
    void naoDeveLancarExcecao_QuandoDataReservaForMenosDe7DiasNoFuturo() {
        LocalDateTime dataValida = LocalDateTime.now().plusDays(6);
        DadosReservaLivro dados = criarDadosReserva(dataValida);
        assertDoesNotThrow(() -> validador.validar(dados));
    }
}
