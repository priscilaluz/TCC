-- MySQL Script generated by MySQL Workbench
-- Wed Jul 12 22:17:48 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tcc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tcc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tcc` DEFAULT CHARACTER SET utf8 ;
USE `tcc` ;

-- -----------------------------------------------------
-- Table `tcc`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`USUARIO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NOT NULL,
  `LOGIN` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `SENHA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`ANEXO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`ANEXO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(20) NOT NULL,
  `BYTES` BLOB(16535) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`PERGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`PERGUNTA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  `CATEGORIA` ENUM('P', 'M', 'H', 'G', 'B', 'F', 'Q', 'I') NOT NULL,
  `USUARIO_ID` INT NOT NULL,
  `JUSTIFICATIVA` VARCHAR(1000) NULL,
  `ANEXO_ID` INT NULL,
  `TIPO` ENUM('ME', 'CL') NOT NULL,
  `NIVEL` ENUM('F', 'M', 'D') NOT NULL,
  `DICA` VARCHAR(500) NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PERGUNTA_USUARIO1_idx` (`USUARIO_ID` ASC),
  INDEX `fk_PERGUNTA_ANEXO1_idx` (`ANEXO_ID` ASC),
  CONSTRAINT `fk_PERGUNTA_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `tcc`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERGUNTA_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `tcc`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`RESPOSTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`RESPOSTA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  `CORRETA` VARCHAR(1) NOT NULL,
  `PERGUNTA_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RESPOSTA_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  CONSTRAINT `fk_RESPOSTA_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `tcc`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`CURSO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`CURSO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NOT NULL,
  `ASSUNTO_GERAL` VARCHAR(1000) NULL,
  `COD_ACESSO` VARCHAR(10) NOT NULL,
  `CATEGORIA` ENUM('P', 'M', 'H', 'G', 'B', 'F', 'Q', 'I') NOT NULL,
  `USUARIO_ID` INT NOT NULL,
  `ANEXO_ID` INT NULL,
  `SITUACAO` ENUM('R', 'C') NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CURSO_USUARIO1_idx` (`USUARIO_ID` ASC),
  INDEX `fk_CURSO_ANEXO1_idx` (`ANEXO_ID` ASC),
  CONSTRAINT `fk_CURSO_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `tcc`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CURSO_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `tcc`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`ETAPA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`ETAPA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ASSUNTO` VARCHAR(1000) NOT NULL,
  `NIVEL` INT NOT NULL,
  `JOGO` ENUM('Q', 'A', 'F', 'C') NOT NULL,
  `CURSO_ID` INT NOT NULL,
  `ANEXO_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_CURSO1_idx` (`CURSO_ID` ASC),
  INDEX `fk_ETAPA_ANEXO1_idx` (`ANEXO_ID` ASC),
  CONSTRAINT `fk_ETAPA_CURSO1`
    FOREIGN KEY (`CURSO_ID`)
    REFERENCES `tcc`.`CURSO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `tcc`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`ETAPA_PERGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`ETAPA_PERGUNTA` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `POSICAO` INT NULL,
  `ETAPA_ID` INT NOT NULL,
  `PERGUNTA_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_PERGUNTA_ETAPA1_idx` (`ETAPA_ID` ASC),
  INDEX `fk_ETAPA_PERGUNTA_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  CONSTRAINT `fk_ETAPA_PERGUNTA_ETAPA1`
    FOREIGN KEY (`ETAPA_ID`)
    REFERENCES `tcc`.`ETAPA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_PERGUNTA_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `tcc`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `tcc`.`CURSO_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`CURSO_ALUNO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `POSICAO_ATUAL` INT NOT NULL,
  `PONTUACAO` INT NOT NULL,
  `SITUACAO` ENUM('A', 'C') NOT NULL,
  `CURSO_ID` INT NOT NULL,
  `USUARIO_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CURSO_ALUNO_CURSO1_idx` (`CURSO_ID` ASC),
  INDEX `fk_CURSO_ALUNO_USUARIO1_idx` (`USUARIO_ID` ASC),
  CONSTRAINT `fk_CURSO_ALUNO_CURSO1`
    FOREIGN KEY (`CURSO_ID`)
    REFERENCES `tcc`.`CURSO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CURSO_ALUNO_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `tcc`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`ETAPA_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`ETAPA_ALUNO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PONTUACAO` INT NOT NULL,
  `ETAPA_ID` INT NOT NULL,
  `CURSO_ALUNO_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_ALUNO_ETAPA1_idx` (`ETAPA_ID` ASC),
  INDEX `fk_ETAPA_ALUNO_CURSO_ALUNO1_idx` (`CURSO_ALUNO_ID` ASC),
  CONSTRAINT `fk_ETAPA_ALUNO_ETAPA1`
    FOREIGN KEY (`ETAPA_ID`)
    REFERENCES `tcc`.`ETAPA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_ALUNO_CURSO_ALUNO1`
    FOREIGN KEY (`CURSO_ALUNO_ID`)
    REFERENCES `tcc`.`CURSO_ALUNO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`PERGUNTA_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`PERGUNTA_ALUNO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PONTUACAO` INT NOT NULL,
  `PULO` VARCHAR(1) NOT NULL,
  `DICA` VARCHAR(1) NOT NULL,
  `ETAPA_ALUNO_ID` INT NOT NULL,
  `PERGUNTA_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PERGUNTA_ALUNO_ETAPA_ALUNO1_idx` (`ETAPA_ALUNO_ID` ASC),
  INDEX `fk_PERGUNTA_ALUNO_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  CONSTRAINT `fk_PERGUNTA_ALUNO_ETAPA_ALUNO1`
    FOREIGN KEY (`ETAPA_ALUNO_ID`)
    REFERENCES `tcc`.`ETAPA_ALUNO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERGUNTA_ALUNO_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `tcc`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tcc`.`RESPOSTA_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tcc`.`RESPOSTA_ALUNO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PERGUNTA_ALUNO_ID` INT NOT NULL,
  `RESPOSTA_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RESPOSTA_ALUNO_PERGUNTA_ALUNO1_idx` (`PERGUNTA_ALUNO_ID` ASC),
  INDEX `fk_RESPOSTA_ALUNO_RESPOSTA1_idx` (`RESPOSTA_ID` ASC),
  CONSTRAINT `fk_RESPOSTA_ALUNO_PERGUNTA_ALUNO1`
    FOREIGN KEY (`PERGUNTA_ALUNO_ID`)
    REFERENCES `tcc`.`PERGUNTA_ALUNO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESPOSTA_ALUNO_RESPOSTA1`
    FOREIGN KEY (`RESPOSTA_ID`)
    REFERENCES `tcc`.`RESPOSTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `tcc`.`usuario` 
ADD COLUMN `TIPO` ENUM('A', 'P') NULL DEFAULT NULL AFTER `SENHA`;

ALTER TABLE `tcc`.`usuario` 
CHANGE COLUMN `TIPO` `TIPO` ENUM('A', 'P', 'T', 'D') NOT NULL DEFAULT 'A' COMMENT '\'A\'- Aluno, \'P\'- Professor, \'T\'- Ambos, aluno e professor' ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- MySQL Workbench Synchronization
-- Generated: 2017-10-15 19:11
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Priscila

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `tcc`.`resposta_aluno` 
DROP FOREIGN KEY `fk_RESPOSTA_ALUNO_PERGUNTA_ALUNO1`,
DROP FOREIGN KEY `fk_RESPOSTA_ALUNO_RESPOSTA1`;

ALTER TABLE `tcc`.`curso` 
CHANGE COLUMN `SITUACAO` `SITUACAO` ENUM('R', 'C') NOT NULL COMMENT '\'R\'-Rascunho, \'C\'-Completo' ;

ALTER TABLE `tcc`.`curso_aluno` 
CHANGE COLUMN `SITUACAO` `SITUACAO` ENUM('A', 'C') NOT NULL COMMENT '\'A\'-Em Andamento, \'C\'-Concluído' ;

ALTER TABLE `tcc`.`etapa` 
CHANGE COLUMN `JOGO` `JOGO` ENUM('Q', 'A', 'F', 'C') NOT NULL COMMENT 'Tipo de Jogo: \'Q\'-Quiz, \'A\'-Aposta, \'F\'-Forca, \'C\'-Caça Palavras' ;

ALTER TABLE `tcc`.`pergunta` 
CHANGE COLUMN `CATEGORIA` `CATEGORIA` ENUM('P', 'M', 'H', 'G', 'B', 'F', 'Q', 'I') NOT NULL COMMENT '\'P\'-Português, \'M\'-Matemática, \'H\'-História, \'G\'-Geografia, \'B\'-Biologia, \'F\'-Física, \'Q\'-Química, \'I\'-Inglês)' ,
CHANGE COLUMN `TIPO` `TIPO` ENUM('ME', 'CL') NOT NULL COMMENT 'Tipo de Pergunta: \'ME\'-multiplas escolhas, \'CL\'-completar lacuna' ,
CHANGE COLUMN `NIVEL` `NIVEL` ENUM('F', 'M', 'D') NOT NULL COMMENT '\'F\'-fácil, \'M\'-médio, \'D\'-difícil' ;

ALTER TABLE `tcc`.`resposta_aluno` 
CHANGE COLUMN `PERGUNTA_ALUNO_ID` `PERGUNTA_ALUNO_ID` INT(11) NOT NULL COMMENT 'ID de Pergunta Aluno' ,
CHANGE COLUMN `RESPOSTA_ID` `RESPOSTA_ID` INT(11) NOT NULL COMMENT 'ID da Resposta' ;

ALTER TABLE `tcc`.`resposta_aluno` 
ADD CONSTRAINT `fk_RESPOSTA_ALUNO_PERGUNTA_ALUNO1`
  FOREIGN KEY (`PERGUNTA_ALUNO_ID`)
  REFERENCES `tcc`.`pergunta_aluno` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_RESPOSTA_ALUNO_RESPOSTA1`
  FOREIGN KEY (`RESPOSTA_ID`)
  REFERENCES `tcc`.`resposta` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `tcc`.`curso` CHANGE COLUMN `ASSUNTO_GERAL` `ASSUNTO_GERAL` VARCHAR(5000) NULL DEFAULT NULL ;

ALTER TABLE `tcc`.`etapa` CHANGE COLUMN `ASSUNTO` `ASSUNTO` VARCHAR(5000) NOT NULL ;

ALTER TABLE `tcc`.`pergunta_aluno` DROP FOREIGN KEY `fk_PERGUNTA_ALUNO_ETAPA_ALUNO1`;

ALTER TABLE `tcc`.`pergunta_aluno` DROP COLUMN `ETAPA_ALUNO_ID`, DROP INDEX `fk_PERGUNTA_ALUNO_ETAPA_ALUNO1_idx` ;

DROP TABLE IF EXISTS `tcc`.`resposta_aluno` ;

CREATE TABLE `tcc`.`relatorio_etapa` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PONTUACAO` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC));

ALTER TABLE `tcc`.`pergunta_aluno` 
ADD COLUMN `TEMPO_ACABOU` VARCHAR(1) NULL AFTER `DICA`,
ADD COLUMN `APOSTAS` VARCHAR(45) NULL AFTER `TEMPO_ACABOU`, RENAME TO  `tcc`.`pergunta_etapa_aluno` ;

ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD COLUMN `relatorio_etapa_ID` INT(11) NOT NULL AFTER `PERGUNTA_ID`,
ADD INDEX `fk_pergunta_etapa_aluno_relatorio_etapa1_idx` (`relatorio_etapa_ID` ASC);

ALTER TABLE `tcc`.`relatorio_etapa` 
ADD COLUMN `etapa_aluno_ID` INT(11) NOT NULL AFTER `PONTUACAO`,
ADD INDEX `fk_relatorio_etapa_etapa_aluno1_idx` (`etapa_aluno_ID` ASC);

ALTER TABLE `tcc`.`resposta` 
ADD COLUMN `pergunta_etapa_aluno_ID` INT(11) NOT NULL AFTER `PERGUNTA_ID`,
ADD INDEX `fk_resposta_pergunta_etapa_aluno1_idx` (`pergunta_etapa_aluno_ID` ASC);

ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD CONSTRAINT `fk_pergunta_etapa_aluno_relatorio_etapa1`
  FOREIGN KEY (`relatorio_etapa_ID`)
  REFERENCES `tcc`.`relatorio_etapa` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `tcc`.`relatorio_etapa` 
ADD CONSTRAINT `fk_relatorio_etapa_etapa_aluno1`
  FOREIGN KEY (`etapa_aluno_ID`)
  REFERENCES `tcc`.`etapa_aluno` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `tcc`.`resposta` 
ADD CONSTRAINT `fk_resposta_pergunta_etapa_aluno1`
  FOREIGN KEY (`pergunta_etapa_aluno_ID`)
  REFERENCES `tcc`.`pergunta_etapa_aluno` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
DROP FOREIGN KEY `fk_pergunta_etapa_aluno_relatorio_etapa1`;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
CHANGE COLUMN `relatorio_etapa_ID` `RELATORIO_ETAPA_ID` INT(11) NOT NULL ;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD CONSTRAINT `fk_pergunta_etapa_aluno_relatorio_etapa1`
  FOREIGN KEY (`RELATORIO_ETAPA_ID`)
  REFERENCES `tcc`.`relatorio_etapa` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



ALTER TABLE `tcc`.`relatorio_etapa` 
DROP FOREIGN KEY `fk_relatorio_etapa_etapa_aluno1`;
ALTER TABLE `tcc`.`relatorio_etapa` 
CHANGE COLUMN `etapa_aluno_ID` `ETAPA_ALUNO_ID` INT(11) NOT NULL ;
ALTER TABLE `tcc`.`relatorio_etapa` 
ADD CONSTRAINT `fk_relatorio_etapa_etapa_aluno1`
  FOREIGN KEY (`ETAPA_ALUNO_ID`)
  REFERENCES `tcc`.`etapa_aluno` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `tcc`.`resposta` 
DROP FOREIGN KEY `fk_resposta_pergunta_etapa_aluno1`;

ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD COLUMN `resposta_ID` INT(11) NOT NULL AFTER `RELATORIO_ETAPA_ID`,
ADD INDEX `fk_pergunta_etapa_aluno_resposta1_idx` (`resposta_ID` ASC);

ALTER TABLE `tcc`.`resposta` 
DROP COLUMN `pergunta_etapa_aluno_ID`,
DROP INDEX `fk_resposta_pergunta_etapa_aluno1_idx` ;

ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD CONSTRAINT `fk_pergunta_etapa_aluno_resposta1`
  FOREIGN KEY (`resposta_ID`)
  REFERENCES `tcc`.`resposta` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
DROP FOREIGN KEY `fk_pergunta_etapa_aluno_resposta1`;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
CHANGE COLUMN `resposta_ID` `RESPOSTA_ID` INT(11) NOT NULL ;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD CONSTRAINT `fk_pergunta_etapa_aluno_resposta1`
  FOREIGN KEY (`RESPOSTA_ID`)
  REFERENCES `tcc`.`resposta` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
DROP FOREIGN KEY `fk_pergunta_etapa_aluno_resposta1`;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
CHANGE COLUMN `RESPOSTA_ID` `RESPOSTA_ESCOLHIDA_ID` INT(11) NULL ;
ALTER TABLE `tcc`.`pergunta_etapa_aluno` 
ADD CONSTRAINT `fk_pergunta_etapa_aluno_resposta1`
  FOREIGN KEY (`RESPOSTA_ESCOLHIDA_ID`)
  REFERENCES `tcc`.`resposta` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `tcc`.`relatorio_etapa` 
ADD COLUMN `GANHOU` VARCHAR(1) NOT NULL AFTER `ETAPA_ALUNO_ID`;

CREATE TABLE IF NOT EXISTS `tcc`.`categoria` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `tcc`.`curso` 
ADD COLUMN `categoria_ID` INT(11) NOT NULL AFTER `SITUACAO`,
ADD INDEX `fk_curso_categoria1_idx` (`categoria_ID` ASC);

ALTER TABLE `tcc`.`pergunta` 
ADD COLUMN `categoria_ID` INT(11) NOT NULL AFTER `DICA`,
ADD INDEX `fk_pergunta_categoria1_idx` (`categoria_ID` ASC);

ALTER TABLE `tcc`.`curso` 
ADD CONSTRAINT `fk_curso_categoria1`
  FOREIGN KEY (`categoria_ID`)
  REFERENCES `tcc`.`categoria` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `tcc`.`pergunta` 
ADD CONSTRAINT `fk_pergunta_categoria1`
  FOREIGN KEY (`categoria_ID`)
  REFERENCES `tcc`.`categoria` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;