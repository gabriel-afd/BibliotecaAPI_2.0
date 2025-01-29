package livros.biblioteca.api.domain.livros;

import livros.biblioteca.api.domain.autor.DadosAutor;

public record LivroAtualizarDTO(

        Long id,
        String title,
        DadosAutor autor,
        Categoria category
) {
}
