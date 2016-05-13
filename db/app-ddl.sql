/*
CREATE GENERATOR gen_mercadoria_id;
SET GENERATOR gen_mercadoria_id TO 0;

CREATE TABLE mercadoria(
id INTEGER NOT NULL,
descricao VARCHAR(255),
nome VARCHAR(200) NOT NULL,
preco FLOAT NOT NULL,
quantidade INTEGER NOT NULL
);
ALTER TABLE mercadoria ADD PRIMARY KEY (id);
*/