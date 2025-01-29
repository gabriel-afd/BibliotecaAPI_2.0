package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;

public interface ValidadorReservas {

    void validar(DadosReservaLivro dadosReservaLivro);
}
