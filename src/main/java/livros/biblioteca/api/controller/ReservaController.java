package livros.biblioteca.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import livros.biblioteca.api.domain.livros.DadosDetalhadosLivro;
import livros.biblioteca.api.domain.reserva.DadosReservaLivro;
import livros.biblioteca.api.domain.reserva.AgendaReservas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reserva")
@SecurityRequirement(name = "bearer-key")
public class ReservaController {

    @Autowired
    private AgendaReservas agendaReservas;

    @PostMapping
    @Transactional
    public ResponseEntity reservarLivro(@RequestBody @Valid DadosReservaLivro dadosReservaLivro){

        var dto = agendaReservas.reservar(dadosReservaLivro);

        return ResponseEntity.ok(dto);
    }
}
