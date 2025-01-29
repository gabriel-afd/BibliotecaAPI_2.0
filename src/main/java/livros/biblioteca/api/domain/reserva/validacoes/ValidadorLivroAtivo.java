package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.livros.LivroRepository;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorLivroAtivo implements ValidadorReservas{

    @Autowired
    private LivroRepository livroRepository;

    public void validar(DadosReservaLivro dadosReservaLivro){
        if (dadosReservaLivro.idLivro() == null){
            return;
        }

        var livroAtivo = livroRepository.findAtivoById(dadosReservaLivro.idLivro());
        if(!livroAtivo){
            throw new ValidacaoException("O livro que você selecinou não está mais disponivel no catalogo");


        }
    }


}
