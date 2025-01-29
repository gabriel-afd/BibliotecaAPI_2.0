package livros.biblioteca.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import livros.biblioteca.api.domain.livros.*;
import livros.biblioteca.api.domain.livros.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/livros")
@SecurityRequirement(name = "bearer-key")
public class LivrosController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarLivros(@RequestBody @Valid LivroDTO dados, UriComponentsBuilder uriBuilder){

        var livro = new Livro(dados);
        livroRepository.save(livro);
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadosLivro(livro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLivros>> listagemLivros(Pageable paginacao){
       var page = livroRepository.findAllByAtivoTrue(paginacao).map(DadosListagemLivros::new);

       return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarLivros(@RequestBody LivroAtualizarDTO dados){

        var livros = livroRepository.getReferenceById(dados.id());
        livros.atualizarLivros(dados);

        return ResponseEntity.ok(new DadosDetalhadosLivro(livros));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLivros(@PathVariable Long id){
        var livros = livroRepository.getReferenceById(id);
        livros.excluirLivroos();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalharLivros(@PathVariable Long id){
        var livros = livroRepository.getReferenceById(id);


        return ResponseEntity.ok(new DadosDetalhadosLivro(livros));

    }



}
