package livros.biblioteca.api.domain.reserva;

import livros.biblioteca.api.domain.livros.Livro;
import livros.biblioteca.api.domain.reserva.validacoes.ValidadorReservas;
import livros.biblioteca.api.domain.livros.LivroRepository;
import livros.biblioteca.api.domain.usuario.UsuarioRepository;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import livros.biblioteca.api.leitor.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaReservas {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    @Autowired
    List<ValidadorReservas> validadorReservas;

    public DadosReservaLivro reservar(DadosReservaLivro dadosReservaLivro){
        if(!livroRepository.existsById(dadosReservaLivro.idLivro())){
            throw new ValidacaoException("Id do livro informado não existe");
        }

        validadorReservas.forEach(vr -> vr.validar(dadosReservaLivro));

        var livro = escolherLivro(dadosReservaLivro);
        var leitor = leitorRepository.getReferenceById(dadosReservaLivro.idLeitor());
        var reserva = new ReservaLivro(null,livro,leitor,dadosReservaLivro.data() ,dadosReservaLivro.qtdDias());
        repository.save(reserva);

        return new DadosReservaLivro(reserva);
    }

    private Livro escolherLivro(DadosReservaLivro dadosReservaLivro) {
        if(dadosReservaLivro.idLivro() != null && livroRepository.existsById(dadosReservaLivro.idLivro())){
            return livroRepository.getReferenceById(dadosReservaLivro.idLivro());
        }
        if(dadosReservaLivro.category() == null){
            throw new ValidacaoException("O livro que quer está ocupado, poderá escolher qualquer um da mesma categoria");
        }

        return livroRepository.escolhaAleatoriaDeLivroMesmaCategoria(dadosReservaLivro.category(),dadosReservaLivro.data());

    }


}
