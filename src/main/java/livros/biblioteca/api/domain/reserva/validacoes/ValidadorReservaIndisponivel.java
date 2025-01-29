package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.reserva.ReservaRepository;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorReservaIndisponivel implements ValidadorReservas{

    @Autowired
    private ReservaRepository repository;

    public void validar(DadosReservaLivro dadosReservaLivro){
        var reservaIndisponivel = repository.existsByLivroIdAndData(dadosReservaLivro.idLivro(),dadosReservaLivro.data());
        if(reservaIndisponivel){
            throw new ValidacaoException("Livro já reservado!");
        }
    }
}
