package livros.biblioteca.api.livros;


import livros.biblioteca.api.autor.Autor;

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