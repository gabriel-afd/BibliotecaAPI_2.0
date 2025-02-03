package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.livros.LivroRepository;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidadorLivroAtivoTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private ValidadorLivroAtivo validadorLivroAtivo;

    @Test
    void deveriaValidarLivroAtivo() {
        // Dados de entrada
        DadosReservaLivro dadosReservaLivro = new DadosReservaLivro(1L, 1L, 1L, null, 10L, null);

        // Mockando o comportamento do repository para retornar verdadeiro (livro ativo)
        when(livroRepository.findAtivoById(1L)).thenReturn(true);

        // Chamando o método de validação
        assertDoesNotThrow(() -> validadorLivroAtivo.validar(dadosReservaLivro));

        // Verificando se o método foi chamado corretamente
        verify(livroRepository, times(1)).findAtivoById(1L);
    }

    @Test
    void deveriaLancarExcecaoQuandoLivroNaoAtivo() {
        // Dados de entrada
        DadosReservaLivro dadosReservaLivro = new DadosReservaLivro(1L, 1L, 1L, null, 10L, null);

        // Mockando o comportamento do repository para retornar falso (livro não ativo)
        when(livroRepository.findAtivoById(1L)).thenReturn(false);

        // Chamando o método de validação e verificando a exceção
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> validadorLivroAtivo.validar(dadosReservaLivro));

        // Verificando se a mensagem da exceção é a esperada
        assertEquals("O livro que você selecinou não está mais disponivel no catalogo", exception.getMessage());
    }

    @Test
    void naoDeveriaValidarQuandoIdLivroForNulo() {
        // Dados de entrada com idLivro nulo
        DadosReservaLivro dadosReservaLivro = new DadosReservaLivro(1L, null, 1L, null, 10L, null);

        // Chamando o método de validação e verificando que não lança exceção
        assertDoesNotThrow(() -> validadorLivroAtivo.validar(dadosReservaLivro));

        // Verificando se o método do repository não foi chamado
        verify(livroRepository, never()).findAtivoById(anyLong());
    }
}
