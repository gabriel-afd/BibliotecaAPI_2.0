package livros.biblioteca.api.domain.autor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Autor {

    private String name;
    private String nationality;

    //public Autor(){}

    public Autor(DadosAutor dadosAutor) {
        this.name = dadosAutor.name();
        this.nationality = dadosAutor.nationality();

    }


    public  void atualizarAutor(DadosAutor dadosAutor) {
        if(dadosAutor.name() != null){
            this.name = dadosAutor.name();
        }

        if(dadosAutor.nationality() != null){
            this.nationality = dadosAutor.nationality();
        }
    }
}
