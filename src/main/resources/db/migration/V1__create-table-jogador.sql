CREATE TABLE tb_jogadores (
    id VARCHAR PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefone VARCHAR(255),
    codinome VARCHAR(255),
    arquivo_referencia VARCHAR(255)
);