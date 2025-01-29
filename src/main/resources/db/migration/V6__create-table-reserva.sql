CREATE TABLE reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID autoincrementado
    livro_id BIGINT NOT NULL,             -- Relacionamento com a tabela livros
    leitor_id BIGINT NOT NULL,            -- Relacionamento com a tabela leitores
    data DATETIME NOT NULL,               -- Data da reserva
    qtd_dias BIGINT,                      -- Quantidade de dias da reserva
    CONSTRAINT fk_reserva_livro FOREIGN KEY (livro_id) REFERENCES livros (id) ON DELETE CASCADE,
    CONSTRAINT fk_reserva_leitor FOREIGN KEY (leitor_id) REFERENCES leitor (id) ON DELETE CASCADE
);
