CREATE SCHEMA tcc;

DROP TABLE IF EXISTS tcc.USUARIO;

CREATE TABLE tcc.USUARIO (
  ID                    INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NOME                  VARCHAR(45) NOT NULL COMMENT 'Nome do usuário',
  LOGIN                 VARCHAR(45) NOT NULL COMMENT 'Lohin do usuário',
  EMAIL                 VARCHAR(45) NOT NULL COMMENT 'Email do usuário',
  SENHA                 VARCHAR(45) NOT NULL COMMENT 'Senha do usuário'
)
