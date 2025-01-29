package livros.biblioteca.api.domain.livros;


import livros.biblioteca.api.domain.autor.Autor;

public record DadosListagemLivros(
        Long id,
        String title,
        Autor autor,
        Categoria category

){
    public DadosListagemLivros(Livro livro){
        this(livro.getId(), livro.getTitle(),livro.getAutor(),livro.getCategory());
    }

}