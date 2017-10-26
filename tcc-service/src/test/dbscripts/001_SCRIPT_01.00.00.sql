DROP TABLE IF EXISTS test.USUARIO;
DROP TABLE IF EXISTS test.ANEXO;
DROP TABLE IF EXISTS test.PERGUNTA;
DROP TABLE IF EXISTS test.RESPOSTA;
DROP TABLE IF EXISTS test.CURSO;
DROP TABLE IF EXISTS test.ETAPA;
DROP TABLE IF EXISTS test.CURSO_ALUNO;
DROP TABLE IF EXISTS test.ETAPA_PERGUNTA;
DROP TABLE IF EXISTS test.ETAPA_ALUNO;
DROP TABLE IF EXISTS test.PERGUNTA_ALUNO;
DROP TABLE IF EXISTS test.RESPOSTA_ALUNO;


CREATE TABLE test.USUARIO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    NOME VARCHAR(45) NOT NULL,
    LOGIN VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(45) NOT NULL,
    TIPO VARCHAR(1) NOT NULL,
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

CREATE TABLE test.CURSO_ALUNO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    CURSO_ID INT NULL,
    USUARIO_ID INT NOT NULL,
    SITUACAO VARCHAR(1) NOT NULL,
    POSICAO_ATUAL INT NOT NULL,
    PONTUACAO INT NOT NULL
);

CREATE TABLE test.ETAPA_ALUNO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    CURSO_ALUNO_ID INT NULL,
    ETAPA_ID INT NOT NULL,
    PONTUACAO INT NOT NULL
);

CREATE TABLE test.PERGUNTA_ALUNO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    ETAPA_ALUNO_ID INT NULL,
    PERGUNTA_ID INT NOT NULL,
    PONTUACAO INT NOT NULL,
    PULO VARCHAR(1) NOT NULL,
    DICA VARCHAR(1) NOT NULL
);

CREATE TABLE test.RESPOSTA_ALUNO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    PERGUNTA_ALUNO_ID INT NULL,
    RESPOSTA_ID INT NOT NULL
);

CREATE TABLE test.PERGUNTA_ETAPA_ALUNO (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    PONTUACAO INT NULL,
    PULO VARCHAR(1) NOT NULL,
    DICA VARCHAR(1) NOT NULL,
    TEMPO_ACABOU VARCHAR(1) NOT NULL,
    APOSTAS VARCHAR(42) NULL,
    PERGUNTA_ID INT NOT NULL,
    RELATORIO_ETAPA_ID INT NOT NULL,
    RESPOSTA_ESCOLHIDA_ID INT NULL
);

CREATE TABLE test.RELATORIO_ETAPA (
    ID INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    PONTUACAO INT NULL,
    ETAPA_ALUNO_ID INT NOT NULL,
    GANHOU VARCHAR(1) NOT NULL
);