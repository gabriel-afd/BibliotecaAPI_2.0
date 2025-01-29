package livros.biblioteca.api.domain.reserva.validacoes;

import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.reserva.ReservaRepository;
import livros.biblioteca.api.domain.usuario.Usuario;
import livros.biblioteca.api.infra.exception.ValidacaoException;
import livros.biblioteca.api.leitor.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorMaximoLivrosPorPessoa implements ValidadorReservas{

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private LeitorRepository leitorRepository;

    public void validar(DadosReservaLivro dadosReservaLivro){

        LocalDateTime dataAtual = LocalDateTime.now();
        Long diasDeDiferenca = java.time.temporal.ChronoUnit.DAYS.between(dataAtual, dadosReservaLivro.data());

        Long livrosReservadosPorUsuario = repository.countByLeitorIdAndDataAfter(dadosReservaLivro.idLeitor(),dataAtual);


        if(livrosReservadosPorUsuario >=2 && diasDeDiferenca < 4){
            throw new ValidacaoException("Você não pode reservar mais de 5 livros");
        }
    }

}
