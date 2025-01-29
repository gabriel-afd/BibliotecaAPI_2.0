package livros.biblioteca.api.leitor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="leitor")
@Entity(name = "Leitor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Leitor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    public Leitor(DadosCadastroLeitor dadosCadastroLeitor) {
        this.nome = dadosCadastroLeitor.nome();
        this.email = dadosCadastroLeitor.email();
    }

    public void atualizarLeitor(DadosCadastroLeitor dadosCadastroLeitor) {
        this.id = dadosCadastroLeitor.id();
        this.nome = dadosCadastroLeitor.nome();
        this.email = dadosCadastroLeitor.email();
    }

    public void excluirLeitor() {
        this.ativo = false;
    }
}
