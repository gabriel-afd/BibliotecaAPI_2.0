package livros.biblioteca.api.domain.livros;

import livros.biblioteca.api.domain.autor.Autor;

public record DadosDetalhadosLivro(
        Long id,
        String title,
        String isbn,
        String language,
        Autor autor,
        DadosPublicacao publication,
        Categoria category
) {

    public DadosDetalhadosLivro(Livro livro){
        this(livro.getId(), livro.getTitle(), livro.getIsbn(), livro.getLanguage(), livro.getAutor(),livro.getPublication(),livro.getCategory());
    }
}
