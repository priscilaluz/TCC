

-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`ANEXO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`ANEXO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`CATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`CATEGORIA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`USUARIO`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`USUARIO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NOT NULL,
  `LOGIN` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `SENHA` VARCHAR(45) NOT NULL,
  `DATA_CADASTRO` DATETIME NOT NULL,
  `DATA_ULTIMO_ACESSO` DATETIME NOT NULL,
  `TIPO` ENUM('A', 'P', 'T', 'D') NOT NULL DEFAULT 'A' COMMENT '\'A\'- Aluno, \'P\'- Professor, \'T\'- Ambos, aluno e professor',
  `AVATAR` ENUM('RE', 'RA', 'IO', 'IA', 'PE') NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`CURSO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`CURSO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(45) NOT NULL,
  `ASSUNTO_GERAL` VARCHAR(5000) NULL DEFAULT NULL,
  `COD_ACESSO` VARCHAR(10) NOT NULL,
  `USUARIO_ID` INT(11) NOT NULL,
  `ANEXO_ID` INT(11) NULL DEFAULT NULL,
  `SITUACAO` ENUM('R', 'C') NOT NULL COMMENT '\'R\'-Rascunho, \'C\'-Completo',
  `CATEGORIA_ID` INT(11) NOT NULL,
  `DISPONIBILIDADE` ENUM('A', 'F', 'E') NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CURSO_USUARIO1_idx` (`USUARIO_ID` ASC),
  INDEX `fk_CURSO_ANEXO1_idx` (`ANEXO_ID` ASC),
  INDEX `fk_curso_categoria1_idx` (`CATEGORIA_ID` ASC),
  CONSTRAINT `fk_CURSO_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `priluz_gameinfor`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CURSO_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `priluz_gameinfor`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_categoria1`
    FOREIGN KEY (`CATEGORIA_ID`)
    REFERENCES `priluz_gameinfor`.`CATEGORIA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`AVISO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`AVISO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TITULO` VARCHAR(100) NOT NULL,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  `DATA_MODIFICACAO` DATETIME NOT NULL,
  `CURSO_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_AVISO_CURSO1_idx` (`CURSO_ID` ASC),
  CONSTRAINT `fk_AVISO_CURSO1`
    FOREIGN KEY (`CURSO_ID`)
    REFERENCES `priluz_gameinfor`.`CURSO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`CURSO_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`CURSO_ALUNO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `POSICAO_ATUAL` INT(11) NOT NULL,
  `PONTUACAO` INT(11) NOT NULL,
  `SITUACAO` ENUM('A', 'C') NOT NULL COMMENT '\'A\'-Em Andamento, \'C\'-Concluído',
  `CURSO_ID` INT(11) NOT NULL,
  `USUARIO_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CURSO_ALUNO_CURSO1_idx` (`CURSO_ID` ASC),
  INDEX `fk_CURSO_ALUNO_USUARIO1_idx` (`USUARIO_ID` ASC),
  CONSTRAINT `fk_CURSO_ALUNO_CURSO1`
    FOREIGN KEY (`CURSO_ID`)
    REFERENCES `priluz_gameinfor`.`CURSO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CURSO_ALUNO_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `priluz_gameinfor`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`ETAPA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`ETAPA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ASSUNTO` VARCHAR(5000) NOT NULL,
  `NIVEL` INT(11) NOT NULL,
  `JOGO` ENUM('Q', 'A', 'F', 'C', 'V') NOT NULL COMMENT 'Tipo de Jogo: \'Q\'-Quiz, \'A\'-Aposta, \'F\'-Forca, \'C\'-Caça Palavras, \'V\'Jogo da velha',
  `CURSO_ID` INT(11) NOT NULL,
  `ANEXO_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_CURSO1_idx` (`CURSO_ID` ASC),
  INDEX `fk_ETAPA_ANEXO1_idx` (`ANEXO_ID` ASC),
  CONSTRAINT `fk_ETAPA_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `priluz_gameinfor`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_CURSO1`
    FOREIGN KEY (`CURSO_ID`)
    REFERENCES `priluz_gameinfor`.`CURSO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`ETAPA_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`ETAPA_ALUNO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `PONTUACAO` INT(11) NOT NULL,
  `ETAPA_ID` INT(11) NOT NULL,
  `CURSO_ALUNO_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_ALUNO_ETAPA1_idx` (`ETAPA_ID` ASC),
  INDEX `fk_ETAPA_ALUNO_CURSO_ALUNO1_idx` (`CURSO_ALUNO_ID` ASC),
  CONSTRAINT `fk_ETAPA_ALUNO_CURSO_ALUNO1`
    FOREIGN KEY (`CURSO_ALUNO_ID`)
    REFERENCES `priluz_gameinfor`.`CURSO_ALUNO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_ALUNO_ETAPA1`
    FOREIGN KEY (`ETAPA_ID`)
    REFERENCES `priluz_gameinfor`.`ETAPA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`PERGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`PERGUNTA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  `USUARIO_ID` INT(11) NOT NULL,
  `JUSTIFICATIVA` VARCHAR(1000) NULL DEFAULT NULL,
  `ANEXO_ID` INT(11) NULL DEFAULT NULL,
  `TIPO` ENUM('ME', 'CL') NOT NULL COMMENT 'Tipo de Pergunta: \'ME\'-multiplas escolhas, \'CL\'-completar lacuna',
  `NIVEL` ENUM('F', 'M', 'D') NOT NULL COMMENT '\'F\'-fácil, \'M\'-médio, \'D\'-difícil',
  `TEMPO` ENUM('T', 'S', 'N') NOT NULL COMMENT '\'T\'-trinta segundos, \'S\'-sessenta segundos, \'N\'-noventa segundos',
  `DICA` VARCHAR(500) NULL DEFAULT NULL,
  `CATEGORIA_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PERGUNTA_USUARIO1_idx` (`USUARIO_ID` ASC),
  INDEX `fk_PERGUNTA_ANEXO1_idx` (`ANEXO_ID` ASC),
  INDEX `fk_pergunta_categoria1_idx` (`CATEGORIA_ID` ASC),
  CONSTRAINT `fk_PERGUNTA_ANEXO1`
    FOREIGN KEY (`ANEXO_ID`)
    REFERENCES `priluz_gameinfor`.`ANEXO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERGUNTA_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `priluz_gameinfor`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_categoria1`
    FOREIGN KEY (`CATEGORIA_ID`)
    REFERENCES `priluz_gameinfor`.`CATEGORIA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`ETAPA_PERGUNTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`ETAPA_PERGUNTA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `POSICAO` INT(11) NULL DEFAULT NULL,
  `ETAPA_ID` INT(11) NOT NULL,
  `PERGUNTA_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ETAPA_PERGUNTA_ETAPA1_idx` (`ETAPA_ID` ASC),
  INDEX `fk_ETAPA_PERGUNTA_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  CONSTRAINT `fk_ETAPA_PERGUNTA_ETAPA1`
    FOREIGN KEY (`ETAPA_ID`)
    REFERENCES `priluz_gameinfor`.`ETAPA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPA_PERGUNTA_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `priluz_gameinfor`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 350
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`RELATORIO_ETAPA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`RELATORIO_ETAPA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `PONTUACAO` VARCHAR(45) NULL DEFAULT NULL,
  `ETAPA_ALUNO_ID` INT(11) NOT NULL,
  `GANHOU` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `fk_relatorio_etapa_etapa_aluno1_idx` (`ETAPA_ALUNO_ID` ASC),
  CONSTRAINT `fk_relatorio_etapa_etapa_aluno1`
    FOREIGN KEY (`ETAPA_ALUNO_ID`)
    REFERENCES `priluz_gameinfor`.`ETAPA_ALUNO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`RESPOSTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`RESPOSTA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(1000) NOT NULL,
  `CORRETA` VARCHAR(1) NOT NULL,
  `PERGUNTA_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RESPOSTA_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  CONSTRAINT `fk_RESPOSTA_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `priluz_gameinfor`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 94
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`PERGUNTA_ETAPA_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`PERGUNTA_ETAPA_ALUNO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `PONTUACAO` INT(11) NOT NULL,
  `PULO` VARCHAR(1) NOT NULL,
  `DICA` VARCHAR(1) NOT NULL,
  `TEMPO_ACABOU` VARCHAR(1) NULL DEFAULT NULL,
  `APOSTAS` VARCHAR(45) NULL DEFAULT NULL,
  `PERGUNTA_ID` INT(11) NOT NULL,
  `RELATORIO_ETAPA_ID` INT(11) NOT NULL,
  `RESPOSTA_ESCOLHIDA_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PERGUNTA_ALUNO_PERGUNTA1_idx` (`PERGUNTA_ID` ASC),
  INDEX `fk_pergunta_etapa_aluno_relatorio_etapa1_idx` (`RELATORIO_ETAPA_ID` ASC),
  INDEX `fk_pergunta_etapa_aluno_resposta1_idx` (`RESPOSTA_ESCOLHIDA_ID` ASC),
  CONSTRAINT `fk_PERGUNTA_ALUNO_PERGUNTA1`
    FOREIGN KEY (`PERGUNTA_ID`)
    REFERENCES `priluz_gameinfor`.`PERGUNTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_etapa_aluno_relatorio_etapa1`
    FOREIGN KEY (`RELATORIO_ETAPA_ID`)
    REFERENCES `priluz_gameinfor`.`RELATORIO_ETAPA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_etapa_aluno_resposta1`
    FOREIGN KEY (`RESPOSTA_ESCOLHIDA_ID`)
    REFERENCES `priluz_gameinfor`.`RESPOSTA` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 152
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `priluz_gameinfor`.`premio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `priluz_gameinfor`.`PREMIO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `USUARIO_ID` INT(11) NOT NULL,
  `QNT_QUIZ_VENCIDOS` INT(11) NOT NULL,
  `QNT_PERG_FACIL_CORRETA` INT(11) NOT NULL,
  `QNT_PERG_MEDIA_CORRETA` INT(11) NOT NULL,
  `QNT_PERG_DIFICIL_CORRETA` INT(11) NOT NULL,
  `QNT_APOSTA_VENCIDAS` INT(11) NOT NULL,
  `QNT_APOSTA_TUDO_PERG_VENCIDAS` INT(11) NOT NULL,
  `QNT_FORCA_VENCIDAS` INT(11) NOT NULL,
  `QNT_CACA_PALAVRAS_VENCIDAS` INT(11) NOT NULL,
  `QNT_JOGO_VELHA_VENCIDAS` INT(11) NOT NULL,
  `QNT_ETAPA_VENCIDA` INT(11) NOT NULL,
  `QNT_CURSO_CONCLUIDO` INT(11) NOT NULL,
  `QNT_ETAPA_SEM_PULO` INT(11) NOT NULL,
  `QNT_ETAPA_SEM_DICA` INT(11) NOT NULL,
  `QNT_PONTOS_ACUMULADOS` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_PREMIO_USUARIO1_idx` (`USUARIO_ID` ASC),
CONSTRAINT `fk_PREMIO_USUARIO1`
    FOREIGN KEY (`USUARIO_ID`)
    REFERENCES `priluz_gameinfor`.`USUARIO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8;


INSERT INTO `priluz_gameinfor`.`USUARIO` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('1', 'adm', 'adm', 'priscila.farias145@gmail.com', '1234pri1234', 'D');