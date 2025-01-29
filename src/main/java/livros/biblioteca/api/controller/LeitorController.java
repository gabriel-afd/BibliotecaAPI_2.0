package livros.biblioteca.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import livros.biblioteca.api.domain.livros.LivroRepository;
import livros.biblioteca.api.leitor.DadosCadastroLeitor;
import livros.biblioteca.api.leitor.Leitor;
import livros.biblioteca.api.leitor.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("leitor")
@SecurityRequirement(name = "bearer-key")
public class LeitorController {

    @Autowired
    private LeitorRepository leitorRepository;

    @Autowired
    private LivroRepository livroRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroLeitor dadosCadastroLeitor, UriComponentsBuilder uriComponentsBuilder){
        var leitor = new Leitor(dadosCadastroLeitor);
        leitorRepository.save(leitor);

        var uri = uriComponentsBuilder.path("/leitor{id}").buildAndExpand(leitor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosCadastroLeitor(leitor));
    }

    @GetMapping
    public ResponseEntity<Page <DadosCadastroLeitor>> listagemLeitor(Pageable pageable){
        var page = leitorRepository.findAll(pageable).map(DadosCadastroLeitor::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarLeitor(@RequestBody DadosCadastroLeitor dadosCadastroLeitor){
        var atualizar = leitorRepository.getReferenceById(dadosCadastroLeitor.id());
        atualizar.atualizarLeitor(dadosCadastroLeitor);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarLeitor(@PathVariable Long id){
        var leitor = leitorRepository.getReferenceById(id);
        leitor.excluirLeitor();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharLeitor(@PathVariable Long id){
        var leitor = leitorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosCadastroLeitor(leitor));
    }
}
