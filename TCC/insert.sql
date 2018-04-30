INSERT INTO `tcc`.`usuario` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('1', 'adm', 'adm', 'adm@email.com', '1234', 'D');
INSERT INTO `tcc`.`usuario` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('2', 'Aluno2', 'aluno2', 'aluno2@email.com', '1234', 'A');
INSERT INTO `tcc`.`usuario` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('3', 'Aluno3', 'aluno3', 'aluno3@email.com', '1234', 'A');
INSERT INTO `tcc`.`usuario` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('4', 'Professor4', 'professor4', 'professor4@email.com', '1234', 'P');
INSERT INTO `tcc`.`usuario` (`ID`, `NOME`, `LOGIN`, `EMAIL`, `SENHA`, `TIPO`) VALUES ('5', 'Professor5', 'professor5', 'professor5@email.com', '1234', 'P');

INSERT INTO `tcc`.`categoria` (`ID`, `DESCRICAO`) VALUES ('1', 'Ingles');
INSERT INTO `tcc`.`categoria` (`ID`, `DESCRICAO`) VALUES ('2', 'Portugues');
INSERT INTO `tcc`.`categoria` (`ID`, `DESCRICAO`) VALUES ('3', 'Matematica');
INSERT INTO `tcc`.`categoria` (`ID`, `DESCRICAO`) VALUES ('4', 'Historia');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('1', 'Pergunta 1', '2', '3', 'Justificatica 1', 'ME', 'F', 'Dica 1');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('1', 'Opções 1 Pergunta 1', 'N', '1');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('2', 'Opções 2 Pergunta 1', 'N', '1');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('3', 'Correta - Opções 3 Pergunta 1', 'S', '1');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('4', 'Opções 4 Pergunta 1', 'N', '1');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('5', 'Opções 5 Pergunta 1', 'N', '1');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('2', 'Pergunta 2', '2', '3', 'Justificatica 2', 'ME', 'D', 'Dica 2');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('6', 'Opções 1 Pergunta 2', 'N', '2');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('7', 'Correta - Opções 2 Pergunta 2', 'S', '2');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('8', 'Opções 3 Pergunta 2', 'N', '2');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('9', 'Opções 4 Pergunta 2', 'N', '2');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('10', 'Opções 5 Pergunta 2', 'N', '2');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('3', 'Pergunta 3', '2', '3', 'Justificatica 3', 'ME', 'F', 'Dica 3');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('11', 'Opções 1 Pergunta 3', 'N', '3');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('12', 'Correta - Opções 2 Pergunta 3', 'S', '3');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('13', 'Opções 3 Pergunta 3', 'N', '3');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('14', 'Opções 4 Pergunta 3', 'N', '3');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('15', 'Opções 5 Pergunta 3', 'N', '3');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('4', 'Pergunta 4', '2', '3', 'Justificatica 4', 'ME', 'D', 'Dica 4');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('16', 'Correta - Opções 1 Pergunta 4', 'S', '4');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('17', 'Opções 2 Pergunta 4', 'N', '4');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('18', 'Opções 3 Pergunta 4', 'N', '4');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('19', 'Opções 4 Pergunta 4', 'N', '4');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('20', 'Opções 5 Pergunta 4', 'N', '4');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('5', 'Pergunta 5', '2', '3', 'Justificatica 5', 'ME', 'F', 'Dica 5');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('21', 'Opções 1 Pergunta 5', 'N', '5');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('22', 'Opções 2 Pergunta 5', 'N', '5');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('23', 'Opções 3 Pergunta 5', 'N', '5');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('24', 'Opções 4 Pergunta 5', 'N', '5');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('25', 'Correta - Opções 5 Pergunta 5', 'S', '5');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('6', 'Pergunta 6', '2', '3', 'Justificatica 6', 'ME', 'D', 'Dica 6');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('26', 'Opções 1 Pergunta 6', 'N', '6');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('27', 'Opções 2 Pergunta 6', 'N', '6');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('28', 'Opções 3 Pergunta 6', 'N', '6');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('29', 'Opções 4 Pergunta 6', 'N', '6');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('30', 'Correta - Opções 5 Pergunta 6', 'S', '6');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('7', 'Pergunta 7', '2', '3', 'Justificatica 7', 'ME', 'F', 'Dica 7');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('31', 'Opções 1 Pergunta 7', 'N', '7');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('32', 'Opções 2 Pergunta 7', 'N', '7');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('33', 'Opções 3 Pergunta 7', 'N', '7');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('34', 'Correta - Opções 4 Pergunta 7', 'S', '7');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('35', 'Opções 5 Pergunta 7', 'N', '7');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('8', 'Pergunta 8', '2', '3', 'Justificatica 8', 'ME', 'D', 'Dica 8');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('36', 'Opções 1 Pergunta 8', 'N', '8');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('37', 'Opções 2 Pergunta 8', 'N', '8');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('38', 'Opções 3 Pergunta 8', 'N', '8');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('39', 'Correta - Opções 4 Pergunta 8', 'S', '8');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('40', 'Opções 5 Pergunta 8', 'N', '8');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('9', 'Pergunta 9', '2', '3', 'Justificatica 9', 'ME', 'F', 'Dica 9');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('41', 'Correta - Opções 1 Pergunta 9', 'S', '9');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('42', 'Opções 2 Pergunta 9', 'N', '9');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('43', 'Opções 3 Pergunta 9', 'N', '9');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('44', 'Opções 4 Pergunta 9', 'N', '9');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('45', 'Opções 5 Pergunta 9', 'N', '9');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('10', 'Pergunta 10', '2', '3', 'Justificatica 10', 'ME', 'D', 'Dica 10');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('46', 'Opções 1 Pergunta 10', 'N', '10');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('47', 'Correta - Opções 2 Pergunta 10', 'S', '10');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('48', 'Opções 3 Pergunta 10', 'N', '10');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('49', 'Opções 4 Pergunta 10', 'N', '10');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('50', 'Opções 5 Pergunta 10', 'N', '10');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('11', 'Pergunta 11 - Resposta: Água', '2', '3', 'Justificatica 11', 'CL', 'D', 'Dica 11');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('51', 'Água', 'S', '11');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('12', 'Pergunta 12 - Resposta: Computador', '2', '3', 'Justificatica 12', 'CL', 'D', 'Dica 12');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('52', 'Computador', 'S', '12');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('13', 'Pergunta 13 - Resposta: Clonar', '2', '3', 'Justificatica 13', 'CL', 'D', 'Dica 13');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('53', 'Clonar', 'S', '13');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('14', 'Pergunta 14 - Resposta: Maca', '2', '3', 'Justificatica 14', 'CL', 'D', 'Dica 14');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('54', 'Maca', 'S', '14');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('15', 'Pergunta 15 - Resposta: Facilidade', '2', '3', 'Justificatica 15', 'CL', 'D', 'Dica 15');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('55', 'Facilidade', 'S', '15');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('16', 'Pergunta 16 - Resposta: Comunicação', '2', '3', 'Justificatica 16', 'CL', 'D', 'Dica 16');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('56', 'Comunicação', 'S', '16');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('17', 'Pergunta 17 - Resposta: House', '2', '3', 'Justificatica 17', 'CL', 'D', 'Dica 17');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('57', 'House', 'S', '17');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('18', 'Pergunta 18 - Resposta: Celular', '2', '3', 'Justificatica 18', 'CL', 'D', 'Dica 18');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('58', 'Celular', 'S', '18');

INSERT INTO `tcc`.`pergunta` (`ID`, `DESCRICAO`, `CATEGORIA_ID`, `USUARIO_ID`, `JUSTIFICATIVA`, `TIPO`, `NIVEL`, `DICA`) 
					  VALUES ('19', 'Pergunta 19 - Resposta: Espelho', '2', '3', 'Justificatica 19', 'CL', 'D', 'Dica 19');
INSERT INTO `tcc`.`resposta` (`ID`, `DESCRICAO`, `CORRETA`, `PERGUNTA_ID`) VALUES ('59', 'Espelho', 'S', '19');

INSERT INTO `tcc`.`curso` (`ID`, `NOME`, `ASSUNTO_GERAL`, `COD_ACESSO`, `CATEGORIA_ID`, `USUARIO_ID`, `SITUACAO`, `DISPONIBILIDADE`) VALUES ('1', 'Lorem Ipsum é simplesmente Lorem Ipsum é simp', 'Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.', 'AAAAA12345', '2', '4', 'C', 'A');

INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('1', 'É um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de letras, ao contrário de Conteúdo aqui, conteúdo aqui, fazendo com que ele tenha uma aparência similar a de um texto legível. Muitos softwares de publicação e editores de páginas na internet agora usam Lorem Ipsum como texto-modelo padrão, e uma rápida busca por  mostra vários websites ainda em sua fase de construção. Várias versões novas surgiram ao longo dos anos, eventualmente por acidente, e às vezes de propósito (injetando humor, e coisas do gênero).', '1', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('2', 'Ao contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico. Com mais de 2000 anos, suas raízes podem ser encontradas em uma obra de literatura latina clássica datada de 45 AC. Richard McClintock, um professor de latim do Hampden-Sydney College na Virginia, pesquisou uma das mais obscuras palavras em latim, consectetur, oriunda de uma passagem de Lorem Ipsum, e, procurando por entre citações da palavra na literatura clássica, descobriu a sua indubitável origem. Lorem Ipsum vem das seções 1.10.32 e 1.10.33 do de Finibus Bonorum et Malorum (Os Extremos do Bem e do Mal), de Cícero, escrito em 45 AC. Este livro é um tratado de teoria da ética muito popular na época da Renascença. A primeira linha de Lorem Ipsum, Lorem Ipsum dolor sit amet... vem de uma linha na seção 1.10.32.', '2', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('3', 'O trecho padrão original de Lorem Ipsum, usado desde o século XVI, está reproduzido abaixo para os interessados. Seções 1.10.32 e 1.10.33 de de Finibus Bonorum et Malorum de Cicero também foram reproduzidas abaixo em sua forma exata original, acompanhada das versões para o inglês da tradução feita por H. Rackham em 1914.\nO trecho padrão original de Lorem Ipsum, usado desde o século XVI, está reproduzido abaixo para os interessados. Seções 1.10.32 e 1.10.33 de de Finibus Bonorum et Malorum de Cicero também foram reproduzidas abaixo em sua forma exata original, acompanhada das versões para o inglês da tradução feita por H. Rackham em 1914.\nO trecho padrão original de Lorem Ipsum, usado desde o século XVI, está reproduzido abaixo para os interessados. Seções 1.10.32 e 1.10.33 de de Finibus Bonorum et Malorum de Cicero também foram reproduzidas abaixo em sua forma exata original, acompanhada das versões para o inglês da tradução feita por H. Rackham em 1914.\nO trecho padrão originald', '3', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('4', 'Porque nós o usamos?\nÉ um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de letras, ao contrário de Conteúdo aqui, conteúdo aqui, fazendo com que ele tenha uma aparência similar a de um texto legível. Muitos softwares de publicação e editores de páginas na internet agora usam Lorem Ipsum como texto-modelo padrão, e uma rápida busca por  mostra vários websites ainda em sua fase de construção. Várias versões novas surgiram ao longo dos anos, eventualmente por acidente, e às vezes de propósito (injetando humor, e coisas do gênero).Porque nós o usamos?\nÉ um fato conhecido de todos que um leitor se distrairá com o conteúdo de texto legível de uma página quando estiver examinando sua diagramação. A vantagem de usar Lorem Ipsum é que ele tem uma distribuição normal de letras, ao contrário de Conteúdo aqui, conteúdo aq', '4', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('5', 'Onde posso conseguí-lo?\nExistem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens com humor, ou palavras aleatórias que não parecem nem um pouco convincentes. Se você pretende usar uma passagem de Lorem Ipsum, precisa ter certeza de que não há algo embaraçoso escrito escondido no meio do texto. Todos os geradores de Lorem Ipsum na internet tendem a repetir pedaços predefinidos conforme necessário, fazendo deste o primeiro gerador de Lorem Ipsum autêntico da internet. Ele usa um dicionário com mais de 200 palavras em Latim combinado com um punhado de modelos de estrutura de frases para gerar um Lorem Ipsum com aparência razoável, livre de repetições, inserções de humor, palavras não características, etc.Onde posso conseguí-lo?\nExistem muitas variações disponíveis de passagens de Lorem Ipsum, mas a maioria sofreu algum tipo de alteração, seja por inserção de passagens com humor, ou palavras aleatórias qu', '5', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('6', 'Passagem padrão original de Lorem Ipsum, usada desde o século XVI.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Passagem padrão original de Lorem Ipsum, usada desde o século XVI.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserun', '6', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('7', 'Seção 1.10.32 de de Finibus Bonorum et Malorum, escrita por Cícero em 45 AC\n\nSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?Seção 1.10.32 de de Finibus Bonorum et Malorum, escr', '7', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('8', 'Tradução para o inglês por H. Rackha, feita em 1914\n\nBut I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produce', '8', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('9', 'Seção 1.10.33 de de Finibus Bonorum et Malorum, escrita por Cícero em 45 AC\n\nAt vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.\nSeção 1.10.33 de de Finibus Bonorum et Malorum, escrita por Cícero em 45', '9', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('10', 'Tradução para o inglês por H. Rackha, feita em 1914\n\nOn the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasur', '10', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('11', '¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem Ipsum? ¿Qué es Lorem', '11', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('12', 'Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos Por qué lo usamos', '12', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('13', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\nNo hay nadie que ame el dolor mismo, que lo busque, lo encuentre y lo quiera, simplemente porque es el dolor.\nNeque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\nNo hay nadie que ame el dolor mismo, que lo busque, lo encuentre y lo quiera, simplemente porque es el dolor.\nNeque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\nNo hay nadie que ame el dolor mismo, que lo busque, lo encuentre y lo quiera, simplemente porque es el dolor.\nNeque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\nNo hay nadie que ame el dolor mismo, que lo busque, lo encuentre y lo quiera, simplemente porque es el dolor.\nNeque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\nNo hay nadie que ame el dolor mismo, que lo busque, lo encuentre y', '13', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('14', 'Perchè lo utilizziamo Perchè lo utilizziamo Perchè lo utilizziamo Lo scopo dell’utilizzo del Lorem Ipsum è che offre una normale distribuzione delle lettere (al contrario di quanto avviene se si utilizzano brevi frasi ripetute, ad esempio “testo qui”), apparendo come un normale blocco di testo leggibile. Molti software di impaginazione e di web design utilizzano Lorem Ipsum come testo modello. Molte versioni del testo sono state prodotte negli anni, a volte casualmente, a volte di proposito (ad esempio inserendo passaggi ironici).', '14', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('15', 'Etapa 15 In genere, i generatori di testo segnaposto disponibili su internet tendono a ripetere paragrafi predefiniti, rendendo questo il primo vero generatore automatico su intenet. Infatti utilizza un dizionario di oltre 200 vocaboli latini, combinati con un insieme di modelli di strutture di periodi, per generare passaggi di testo verosimili. Il testo così generato è sempre privo di ripetizioni, parole imbarazzanti o fuori luogo ecc.', '15', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('16', 'Etapa 16 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 Il brano standard del Lorem Ipsum usato sin dal sedicesimo secolo è riprodotto qui di seguito per coloro che fossero interessati. Anche le sezioni 1.10.32 e 1.10.33 I', '16', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('17', 'Se si decide di utilizzare un passaggio del Lorem Ipsum, è bene essere certi che non contenga nulla di imbarazzante. In genere, i generatori di testo segnaposto disponibili su internet tendono a ripetere paragrafiSe si decide di utilizzare un passaggio del Lorem Ipsum, è bene essere certi che non contenga nulla di imbarazzante. In genere, i generatori di testo segnaposto disponibili su internet tendono a ripetere paragrafiSe si decide di utilizzare un passaggio del Lorem Ipsum, è bene essere certi che non contenga nulla di imbarazzante. In genere, i generatori di testo segnaposto disponibili su internet tendono a ripetere paragrafi', '17', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('18', 'Etapa 18 Il passaggio standard del Lorem Ipsum, utilizzato sin dal sedicesimo secoloEtapa 18 Il passaggio standard del Lorem Ipsum, utilizzato sin dal sedicesimo secoloEtapa 18 Il passaggio standard del Lorem Ipsum, utilizzato sin dal sedicesimo secoloEtapa 18 Il passaggio standard del Lorem Ipsum, utilizzato sin dal sedicesimo secolo', '18', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('19', 'Traduzione del 1914 di H. Rackham\n\nBut I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant ple', '19', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('20', 'La sezione 1.10.33 del de Finibus Bonorum et Malorum, scritto da Cicerone nel 45 AC\n\nAt vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.', '20', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('21', 'Traduzione del 1914 di H. Rackham\n\nBut I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant ple', '21', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('22', 'La sezione 1.10.32 del de Finibus Bonorum et Malorum, scritto da Cicerone nel 45 AC\n\nSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?', '22', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('23', 'Il passaggio standard del Lorem Ipsum, utilizzato sin dal sedicesimo secolo\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '23', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('24', 'Supporta il sito: se utilizzi questo sito regolarmente e vorresti aiutarci a mantenerlo attivo, puoi versare una piccolo somma per contribuire alle spese di hosting e di traffico. Non c’è una somma minima, ogni importo è apprezzato. Clicca qui per contribuire utilizzando PayPal. Grazie per la collaborazione. Supporta il sito: se utilizzi questo sito regolarmente e vorresti aiutarci a mantenerlo attivo, puoi versare una piccolo somma per contribuire alle spese di hosting e di traffico. Non c’è una somma minima, ogni importo è apprezzato. Clicca qui per contribuire utilizzando PayPal. Grazie per la collaborazione.', '24', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('25', 'Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni: Ci puoi aiutare a tradurre questo sito in una lingua straniera? Scrivici una email con i dettagli.Traduzioni', '25', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('26', 'Este pur şi simplu o machetă pentru text a industriei tipografice. Lorem Ipsum a fost macheta standard a industriei încă din secolul al XVI-lea, când un tipograf anonim a luat o planşetă de litere şi le-a amestecat pentru a crea o carte demonstrativă pentru literele respective. Nu doar că a supravieţuit timp de cinci secole, dar şi a facut saltul în tipografia electronică practic neschimbată. A fost popularizată în anii 0 odată cu ieşirea colilor Letraset care conţineau pasaje Lorem Ipsum, iar mai recent, prin programele de publicare pentru calculator, ca Aldus PageMaker care includeau versiuni de Lorem Ipsum.', '26', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('27', 'Conţinut aici, conţinut acolo, făcându-l să arate ca o engleză citibilă. Multe pachete de publicare pentru calculator şi editoare de pagini web folosesc acum Lorem Ipsum ca model standard de text, iar o cautare de lorem ipsum va rezulta în o mulţime de site-uri web în dezvoltare. Pe parcursul anilor, diferite versiuni au evoluat, uneori din intâmplare, uneori intenţionat (infiltrându-se elemente de umor sau altceva de acest gen).', '27', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('28', 'Trebuie să vă asiguraţi că nu conţine nimic stânjenitor ascuns printre randuri. Toate generatoarele de Lorem Ipsum de pe Internet tind să repete bucăţi de text în funcţie de necesitate, astfel făcându-l pe acesta primul generator adevarat de pe Internet. El utilizează un dicţionar de peste 200 cuvinte din latina, combinate cu o cantitate considerabilă de modele de structuri de propoziţii, pentru a genera Lorem Ipsum care arată decent. Astfel, Lorem Ipsum-ul generat nu conţine repetiţii, infiltrări de elemente de umor sau de cuvinte non-caracteristice, etc.', '28', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('29', 'Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există un set de bannere disponibile în trei culori şi într-o gamă de dimensiuni standard pentru bannere, pe care le puteţi găsi aici. Acum, există.', '29', 'Q', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('30', 'Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis.', '30', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('31', 'Etapa 31 At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.', '31', 'A', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('32', 'Etapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea din 1914 de către H. Rackham\nEtapa 32 Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\nTraducerea.', '32', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('33', 'The other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish he other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguishhe other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by t', '33', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('34', 'In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he reject', '34', 'F', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('35', 'Etapa 35t vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Etapa 35t vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Etapa 35t vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Etapa 35t vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentiu', '35', 'C', '1');
INSERT INTO `tcc`.`etapa` (`ID`, `ASSUNTO`, `NIVEL`, `JOGO`, `CURSO_ID`) VALUES ('36', 'Secţiunea 1.10.33 din de Finibus Bonorum et Malorum, scrisă de către Cicerone în anul 45 î.e.n.\n\nAt vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.Secţiunea 1.10.33 din de Finibus Bonorum et Malorum,', '36', 'C', '1');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('1', '1', '1', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('2', '2', '1', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('3', '3', '1', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('4', '4', '1', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('5', '5', '1', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('6', '6', '1', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('7', '7', '1', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('8', '8', '1', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('9', '9', '1', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('10', '10', '1', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('11', '1', '2', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('12', '2', '2', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('13', '3', '2', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('14', '4', '2', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('15', '5', '2', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('16', '6', '2', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('17', '7', '2', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('18', '8', '2', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('19', '9', '2', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('20', '1', '3', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('21', '2', '3', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('22', '3', '3', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('23', '4', '3', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('24', '5', '3', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('25', '6', '3', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('26', '7', '3', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('27', '8', '3', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('28', '9', '3', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('29', '1', '4', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('30', '2', '4', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('31', '3', '4', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('32', '4', '4', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('33', '5', '4', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('34', '6', '4', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('35', '7', '4', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('36', '8', '4', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('37', '9', '4', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('38', '10', '4', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('39', '1', '5', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('40', '2', '5', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('41', '3', '5', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('42', '4', '5', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('43', '5', '5', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('44', '6', '5', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('45', '7', '5', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('46', '8', '5', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('47', '1', '6', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('48', '2', '6', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('49', '3', '6', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('50', '4', '6', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('51', '5', '6', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('52', '6', '6', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('53', '7', '6', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('54', '8', '6', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('55', '1', '7', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('56', '2', '7', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('57', '3', '7', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('58', '4', '7', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('59', '5', '7', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('60', '6', '7', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('61', '7', '7', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('62', '8', '7', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('63', '9', '7', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('64', '10', '7', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('65', '1', '8', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('66', '2', '8', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('67', '3', '8', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('68', '4', '8', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('69', '5', '8', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('70', '6', '8', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('71', '7', '8', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('72', '8', '8', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('73', '9', '8', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('74', '10', '8', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('75', '1', '9', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('76', '2', '9', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('77', '3', '9', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('78', '4', '9', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('79', '5', '9', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('80', '6', '9', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('81', '7', '9', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('82', '8', '9', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('83', '9', '9', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('84', '1', '10', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('85', '2', '10', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('86', '6', '10', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('87', '7', '10', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('88', '8', '10', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('89', '9', '10', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('90', '1', '11', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('91', '2', '11', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('92', '3', '11', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('93', '4', '11', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('94', '5', '11', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('95', '6', '11', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('96', '7', '11', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('97', '8', '11', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('98', '9', '11', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('99', '1', '12', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('100', '2', '12', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('101', '4', '12', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('102', '5', '12', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('103', '6', '12', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('104', '8', '12', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('105', '9', '12', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('106', '1', '13', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('107', '2', '13', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('108', '3', '13', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('109', '4', '13', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('110', '5', '13', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('111', '6', '13', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('112', '7', '13', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('113', '8', '13', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('114', '9', '13', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('115', '10', '13', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('116', '1', '14', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('117', '2', '14', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('118', '3', '14', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('119', '4', '14', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('120', '5', '14', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('121', '6', '14', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('122', '7', '14', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('123', '8', '14', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('124', '9', '14', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('125', '1', '15', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('126', '2', '15', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('127', '3', '15', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('128', '4', '15', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('129', '5', '15', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('130', '6', '15', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('131', '7', '15', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('132', '8', '15', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('133', '9', '15', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('134', '10', '15', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('135', '1', '16', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('136', '2', '16', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('137', '3', '16', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('138', '4', '16', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('139', '5', '16', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('140', '6', '16', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('141', '7', '16', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('142', '8', '16', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('143', '9', '16', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('144', '10', '16', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('145', '1', '17', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('146', '2', '17', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('147', '3', '17', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('148', '4', '17', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('149', '5', '17', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('150', '6', '17', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('151', '7', '17', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('152', '8', '17', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('153', '9', '17', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('154', '1', '18', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('155', '2', '18', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('156', '3', '18', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('157', '4', '18', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('158', '5', '18', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('159', '6', '18', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('160', '7', '18', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('161', '8', '18', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('162', '9', '18', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('163', '10', '18', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('164', '1', '19', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('165', '2', '19', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('166', '3', '19', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('167', '4', '19', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('168', '5', '19', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('169', '6', '19', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('170', '7', '19', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('171', '8', '19', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('172', '9', '19', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('173', '10', '19', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('174', '1', '20', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('175', '2', '20', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('176', '3', '20', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('177', '4', '20', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('178', '5', '20', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('179', '6', '20', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('180', '7', '20', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('181', '8', '20', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('182', '9', '20', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('183', '1', '21', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('184', '2', '21', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('185', '3', '21', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('186', '4', '21', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('187', '5', '21', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('188', '6', '21', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('189', '7', '21', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('190', '8', '21', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('191', '9', '21', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('192', '1', '22', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('193', '2', '22', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('194', '3', '22', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('195', '4', '22', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('196', '5', '22', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('197', '6', '22', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('198', '7', '22', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('199', '8', '22', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('200', '9', '22', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('201', '10', '22', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('202', '1', '23', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('203', '2', '23', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('204', '3', '23', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('205', '4', '23', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('206', '5', '23', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('207', '6', '23', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('208', '7', '23', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('209', '8', '23', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('210', '9', '23', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('211', '10', '23', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('212', '2', '24', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('213', '3', '24', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('214', '5', '24', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('215', '6', '24', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('216', '8', '24', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('217', '9', '24', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('218', '2', '25', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('219', '3', '25', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('220', '4', '25', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('221', '6', '25', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('222', '7', '25', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('223', '8', '25', '8');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('224', '1', '26', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('225', '2', '26', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('226', '3', '26', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('227', '4', '26', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('228', '5', '26', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('229', '6', '26', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('230', '7', '26', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('231', '8', '26', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('232', '9', '26', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('233', '10', '26', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('234', '1', '27', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('235', '3', '27', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('236', '4', '27', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('237', '8', '27', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('238', '9', '27', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('239', '1', '28', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('240', '2', '28', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('241', '3', '28', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('242', '4', '28', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('243', '5', '28', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('244', '6', '28', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('245', '7', '28', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('246', '8', '28', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('247', '9', '28', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('248', '10', '28', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('249', '1', '29', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('250', '2', '29', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('251', '3', '29', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('252', '4', '29', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('253', '5', '29', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('254', '6', '29', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('255', '7', '29', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('256', '8', '29', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('257', '9', '29', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('258', '10', '29', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('259', '1', '30', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('260', '2', '30', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('261', '3', '30', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('262', '4', '30', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('263', '5', '30', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('264', '6', '30', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('265', '7', '30', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('266', '8', '30', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('267', '9', '30', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('268', '1', '31', '1');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('269', '2', '31', '2');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('270', '3', '31', '3');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('271', '4', '31', '4');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('272', '5', '31', '5');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('273', '6', '31', '6');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('274', '7', '31', '7');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('275', '8', '31', '8');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('276', '9', '31', '9');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('277', '10', '31', '10');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('278', '1', '32', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('279', '2', '32', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('280', '3', '32', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('281', '4', '32', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('282', '5', '32', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('283', '6', '32', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('284', '7', '32', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('285', '8', '32', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('286', '9', '32', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('287', '1', '33', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('288', '2', '33', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('289', '3', '33', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('290', '4', '33', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('291', '5', '33', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('292', '6', '33', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('293', '7', '33', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('294', '8', '33', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('295', '9', '33', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('296', '1', '34', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('297', '2', '34', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('298', '3', '34', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('299', '4', '34', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('300', '5', '34', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('301', '6', '34', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('302', '7', '34', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('303', '1', '35', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('304', '2', '35', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('305', '3', '35', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('306', '4', '35', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('307', '5', '35', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('308', '6', '35', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('309', '7', '35', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('310', '8', '35', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('311', '9', '35', '19');

INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('312', '1', '36', '11');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('313', '2', '36', '12');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('314', '3', '36', '13');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('315', '4', '36', '14');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('316', '5', '36', '15');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('317', '6', '36', '16');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('318', '7', '36', '17');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('319', '8', '36', '18');
INSERT INTO `tcc`.`etapa_pergunta` (`ID`, `POSICAO`, `ETAPA_ID`, `PERGUNTA_ID`) VALUES ('320', '9', '36', '19');