DROP TABLE IF EXISTS test.USUARIO;
DROP TABLE IF EXISTS test.ANEXO;
DROP TABLE IF EXISTS test.PERGUNTA;
DROP TABLE IF EXISTS test.RESPOSTA;
DROP TABLE IF EXISTS test.CURSO;
DROP TABLE IF EXISTS test.ETAPA;
DROP TABLE IF EXISTS test.ETAPA_PERGUNTA;


CREATE TABLE test.USUARIO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    NOME VARCHAR(45) NOT NULL,
    LOGIN VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(45) NOT NULL,
    SENHA VARCHAR(45) NOT NULL
);

CREATE TABLE test.ANEXO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    NOME VARCHAR(45) NOT NULL,
    BYTES VARCHAR(45) NOT NULL
);

CREATE TABLE test.PERGUNTA (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    DESCRICAO VARCHAR(1000) NOT NULL,
    BYTES VARCHAR(45) NOT NULL,
    CATEGORIA VARCHAR(1) NOT NULL,
    NIVEL VARCHAR(1) NOT NULL,
    TIPO VARCHAR(2) NOT NULL,
    DICA VARCHAR(500) NULL,
    USUARIO_ID INT NOT NULL,
    JUSTIFICATIVA VARCHAR(1000) NULL,
    ANEXO_ID INT NULL
);

CREATE TABLE test.RESPOSTA (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL,
    CORRETA VARCHAR(1) NOT NULL,
    PERGUNTA_ID INT NOT NULL
);

CREATE TABLE test.CURSO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    NOME VARCHAR(45) NOT NULL,
    ASSUNTO_GERAL VARCHAR(1000) NULL,
    COD_ACESSO VARCHAR(10) NOT NULL,
    CATEGORIA VARCHAR(1) NOT NULL,
    USUARIO_ID INT NOT NULL,
    ANEXO_ID INT NULL,
    SITUACAO VARCHAR(1) NOT NULL
);

CREATE TABLE test.ETAPA (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ASSUNTO VARCHAR(1000) NOT NULL,
    NIVEL INT NOT NULL,
    JOGO VARCHAR(1) NOT NULL,
    CURSO_ID INT NOT NULL,
    ANEXO_ID INT NOT NULL
);

CREATE TABLE test.ETAPA_PERGUNTA (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    POSICAO INT NULL,
    ETAPA_ID INT NOT NULL,
    PERGUNTA_ID INT NOT NULL
);