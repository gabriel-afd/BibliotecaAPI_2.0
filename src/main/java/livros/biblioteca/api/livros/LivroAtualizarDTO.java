package livros.biblioteca.api.livros;

import livros.biblioteca.api.autor.Autor;
import livros.biblioteca.api.autor.DadosAutor;

public record LivroAtualizarDTO(

        Long id,
        String title,
        DadosAutor autor,
        Categoria category
) {
}
