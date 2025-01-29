package livros.biblioteca.api.leitor;

public record DadosCadastroLeitor(
        Long id,
        String nome,
        String email
){
    public DadosCadastroLeitor(Leitor leitor) {
        this(leitor.getId(), leitor.getNome(), leitor.getEmail());
    }
}
