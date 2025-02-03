package livros.biblioteca.api.domain.reserva;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import livros.biblioteca.api.domain.livros.Livro;
import livros.biblioteca.api.domain.usuario.Usuario;
import livros.biblioteca.api.leitor.Leitor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "reserva")
@Entity(name = "ReservaLivro")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class ReservaLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    private LocalDateTime data;

    private Long qtdDias;

    public ReservaLivro(Long id, Livro livro, Leitor leitor, LocalDateTime data, Long qtdDias){
        this.id = id;
        this.livro = livro;
        this.leitor = leitor;
        this.data = data;
        this.qtdDias = qtdDias;
    }
}
