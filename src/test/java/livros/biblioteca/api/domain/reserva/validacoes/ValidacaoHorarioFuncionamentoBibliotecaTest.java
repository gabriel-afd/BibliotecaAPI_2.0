package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.reserva.ReservaLivro;
import livros.biblioteca.api.domain.livros.Categoria;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidacaoHorarioFuncionamentoBibliotecaTest {

    private ValidacaoHorarioFuncionamentoBiblioteca validador;

    @BeforeEach
    void setUp() {
        validador = new ValidacaoHorarioFuncionamentoBiblioteca();
    }

    private DadosReservaLivro criarDadosReserva(LocalDateTime data) {
        return new DadosReservaLivro(1L, 1L, 1L, data, 7L, Categoria.AVENTURA);
    }

    @Test
    void deveLancarExcecao_QuandoReservaForNoDomingo() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 2, 10, 0)); // Domingo
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoReservaForAntesDoHorarioDeAbertura() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 7, 59)); // Segunda antes das 8h
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoReservaForNoHorarioDeAlmoco() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 12, 30)); // Segunda no almoço
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoReservaForDepoisDoHorarioDeFechamento() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 19, 0)); // Segunda após 18h
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void naoDeveLancarExcecao_QuandoReservaForDentroDoHorarioPermitido() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 10, 0)); // Segunda, horário permitido
        assertDoesNotThrow(() -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoReservaForExatamenteNoInicioDoAlmoco() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 12, 0)); // Segunda, início do almoço
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void deveLancarExcecao_QuandoReservaForExatamenteNoFinalDoAlmoco() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 14, 0)); // Segunda, fim do almoço
        assertThrows(ValidacaoException.class, () -> validador.validar(dados));
    }

    @Test
    void naoDeveLancarExcecao_QuandoReservaForExatamenteNoInicioDoExpediente() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 8, 0)); // Segunda, início do expediente
        assertDoesNotThrow(() -> validador.validar(dados));
    }

    @Test
    void naoDeveLancarExcecao_QuandoReservaForExatamenteNoFinalDoExpediente() {
        DadosReservaLivro dados = criarDadosReserva(LocalDateTime.of(2025, 2, 3, 18, 0)); // Segunda, fim do expediente
        assertDoesNotThrow(() -> validador.validar(dados));
    }
}
