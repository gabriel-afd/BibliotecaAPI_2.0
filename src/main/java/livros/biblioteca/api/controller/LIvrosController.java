package livros.biblioteca.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import livros.biblioteca.api.livros.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/livros")
public class LIvrosController {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarLivros(@RequestBody @Valid LivroDTO dados){
        repository.save(new Livro(dados));
    }

    @GetMapping
    public Page<DadosListagemLivros> listagemLivros(Pageable paginacao){
       return repository.findAllByAtivoTrue(paginacao).map(DadosListagemLivros::new);

    }

    @PutMapping
    @Transactional
    public void atualizarLivros(@RequestBody LivroAtualizarDTO dados){

        var livros = repository.getReferenceById(dados.id());
        livros.atualizarLivros(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirLivros(@PathVariable Long id){
        var livros = repository.getReferenceById(id);
        livros.excluirLivroos();

    }



}
