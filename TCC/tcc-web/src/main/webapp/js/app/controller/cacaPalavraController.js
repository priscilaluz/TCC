tccApp.controller('CacaPalavraController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    var tamanhoMatriz = 12;
    var indexMatriz = -1;
    var corSelecionada = '#5396d4';
    var corQuandoPassarPor = '#9bcffd';
    var corInicial = '#8ab7de';
    var perguntasTotaisEncontradas = 0;
    var palavrasEncontradas = 0;
    var coresSelecionadas = ['#5f3257','#356b23','#9e9b22','#de968a','#be8ade','#9a5316','#7da938','#d856ce','#5356ca','#04c71c','#ec0b7e','#ec7d1d'];
    var pontuacaoMinima = 0;
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var aberto = $routeParams.aberto;
    $scope.count = 0;
    $scope.imagem1 = true;
    $scope.model = {
        jogo:"Caça Palavra",
        pontuacao: 0,
        qntDica: 1,
        primeiraCedula: null,
        matrizCompleta: null,
        perdeuJogo: false,
        resultado: false,
        anexosString: [],
        cacaPalavraLista: [],
        perguntas: [],
        resultados: []
    };
    
    $scope.mostrarResultado = function () {
        $scope.model.mostrarRelatorio = true;
    };
    $scope.voltarTabuleiro = function () {
        $location.path("/aluno-cursando/"+idCursoAluno);
    };
    
    $scope.voltar = function () {
        if (idCursoAluno && idEtapa){
            $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto);
        } else {
            $location.path("/jogos");
        }
    };

    var posicaoDicaPergunta = -1;
    $scope.dicaPergunta = function () {
        var dica = "Não existe dicas disponíveis.";
        var obj = {'dica': dica};
        for (var x = 0; x <= $scope.model.perguntas.length; x++) {
            if (!$scope.model.perguntas[x].style && $scope.model.perguntas[x].dica){
                posicaoDicaPergunta = x;
                var pergunta = "Pergunta: "+$scope.model.perguntas[x].descricao;
                dica = $scope.model.perguntas[x].dica;
                obj = {'dica': dica, 'pergunta': pergunta};
                $scope.model.dica = true;
                $scope.model.qntDica--;
                $scope.model.pontuacao = $scope.model.pontuacao-25;
                break;
            }
        }
        //$scope.obj = obj;
        $modal.open({
            templateUrl: 'partials/jogo/dica.html',
            controller: 'DicaController',
            resolve: {obj: function () {return obj;}}
        }).result.then(function (result) {}, function () {});
    };
    
    $scope.mouseDownCelula = function (i, j) {
        $scope.model.primeiraCedula = null;
        backgroundMatriz();
        $scope.model.primeiraCedula = {x:i, y:j, letra: angular.copy($scope.model.matrizCompleta[i][j])};
        $scope.model.matrizCompleta[i][j].selecionado=true;
        $scope.model.matrizCompleta[i][j].style = {'background-color': corSelecionada};
    };
    
    $scope.mouseUpCelula = function (i, j) {
        var listaDeXY = [];
        if (j >= 0 && i >= 0 && $scope.model.primeiraCedula){
            var palavra = "";
            if (i === $scope.model.primeiraCedula.x) {
                var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;
                var yMaior = $scope.model.primeiraCedula.y > j ? $scope.model.primeiraCedula.y : j;
                for (var b = yMenor; b <= yMaior; b++) {
                    listaDeXY.push({x:i, y:b});
                    palavra = palavra + $scope.model.matrizCompleta[i][b].letra;
                }
            } else if (j === $scope.model.primeiraCedula.y) {
                var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                var xMaior = $scope.model.primeiraCedula.x > i ? $scope.model.primeiraCedula.x : i;
                for (var a = xMenor; a <= xMaior; a++) {
                    listaDeXY.push({x:a, y:j});
                    palavra = palavra + $scope.model.matrizCompleta[a][j].letra;
                }
            } else {
                var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                if (($scope.model.primeiraCedula.x-i) === ($scope.model.primeiraCedula.y-j)) {
                    var diagonal = ($scope.model.primeiraCedula.x-i);
                    diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                    var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                    var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;

                    for (var a = 0; a <= diagonal; a++) {
                        listaDeXY.push({x:xMenor+a, y:yMenor+a});
                        palavra = palavra + $scope.model.matrizCompleta[xMenor+a][yMenor+a].letra;
                    }
                } else {
                    var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                    var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                    if (xMenosI === yMenosJ) {
                        var diagonal = ($scope.model.primeiraCedula.x-i);
                        diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                        var xInicio = i;
                        var yInicio = j;

                        if ($scope.model.primeiraCedula.x < i) {
                            xInicio = $scope.model.primeiraCedula.x;
                            yInicio = $scope.model.primeiraCedula.y;
                        }

                        for (var a = 0; a <= diagonal; a++) {
                            listaDeXY.push({x:xInicio+a, y:yInicio-a});
                            palavra = palavra + $scope.model.matrizCompleta[xInicio+a][yInicio-a].letra;
                        }

                    }
                }
            }
            $scope.palavra = palavra;
            var palavraExiste = false;
            var m = 0;
            for (var m = 0; m < $scope.model.perguntas.length; m++) {
                var resposta = $scope.model.perguntas[m].respostas[0].descricao.toUpperCase();
                if ((resposta === palavra || resposta === reverse(palavra)) && !$scope.model.perguntas[m].style) {
                    $scope.model.pontuacao = $scope.model.pontuacao + 100;
                    palavraExiste = true;
                    break;
                }
            }
            if (palavraExiste && !$scope.model.matrizCompleta[listaDeXY[0].x][listaDeXY[0].y].comPalavra) {
                for (var k = 0; k < listaDeXY.length; k++) {
                    var posicaoX = listaDeXY[k].x;
                    var posicaoY = listaDeXY[k].y;
                    $scope.model.matrizCompleta[posicaoX][posicaoY].comPalavra = coresSelecionadas[palavrasEncontradas];
                }
                $scope.model.perguntas[m].style = {'background-color': coresSelecionadas[palavrasEncontradas], 'color':'#fff'};
                perguntasTotaisEncontradas++;
                palavrasEncontradas++;
                barraDeProgresso();
            }
            $scope.model.primeiraCedula = null;
            backgroundMatriz();
            if (palavrasEncontradas === $scope.model.perguntas.length) {
                mudarMatriz();
            }
        }
    };
    
    var reverse = function (s){
        return s.split("").reverse().join("");
    };
    
    $scope.mouseOverCelula = function (i, j) {
        if ($scope.model.primeiraCedula) {
            backgroundMatriz();
            if (i === $scope.model.primeiraCedula.x) {
                var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;
                var yMaior = $scope.model.primeiraCedula.y > j ? $scope.model.primeiraCedula.y : j;
                for (var b = yMenor; b <= yMaior; b++) {
                    $scope.model.matrizCompleta[i][b].selecionado = true;
                    $scope.model.matrizCompleta[i][b].style = {'background-color': corSelecionada};
                }
            } else if (j === $scope.model.primeiraCedula.y) {
                var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                var xMaior = $scope.model.primeiraCedula.x > i ? $scope.model.primeiraCedula.x : i;
                for (var a = xMenor; a <= xMaior; a++) {
                    $scope.model.matrizCompleta[a][j].selecionado = true;
                    $scope.model.matrizCompleta[a][j].style = {'background-color': corSelecionada};
                }
            } else {
                var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                if (($scope.model.primeiraCedula.x-i) === ($scope.model.primeiraCedula.y-j)) {
                    var diagonal = ($scope.model.primeiraCedula.x-i);
                    diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                    var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                    var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;

                    for (var a = 0; a <= diagonal; a++) {
                        $scope.model.matrizCompleta[xMenor+a][yMenor+a].selecionado = true;
                        $scope.model.matrizCompleta[xMenor+a][yMenor+a].style = {'background-color': corSelecionada};
                    }
                } else {
                    var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                    var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                    if (xMenosI === yMenosJ) {
                        var diagonal = ($scope.model.primeiraCedula.x-i);
                        diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                        var xInicio = i;
                        var yInicio = j;
                        
                        if ($scope.model.primeiraCedula.x < i) {
                            xInicio = $scope.model.primeiraCedula.x;
                            yInicio = $scope.model.primeiraCedula.y;
                        }
                        
                        for (var a = 0; a <= diagonal; a++) {
                            $scope.model.matrizCompleta[xInicio+a][yInicio-a].selecionado = true;
                            $scope.model.matrizCompleta[xInicio+a][yInicio-a].style = {'background-color': corSelecionada};
                        }

                    }
                }
            }
        } else {
            if (!$scope.model.matrizCompleta[i][j].comPalavra) {
                $scope.model.matrizCompleta[i][j].style = {'background-color': corQuandoPassarPor};
            }
        }
    };
    
    $scope.mouseLeaveCelula = function (i, j) {
        if (!$scope.model.primeiraCedula && !$scope.model.matrizCompleta[i][j].comPalavra) {
            $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
        } else if ($scope.model.matrizCompleta[i][j].comPalavra) {
            $scope.model.matrizCompleta[i][j].style = {'background-color': $scope.model.matrizCompleta[i][j].comPalavra};
        }
    };
    
    var backgroundMatriz = function () {
        for (var i = 0; i < tamanhoMatriz; i++) {
            for (var j = 0; j < tamanhoMatriz; j++) {
                $scope.model.matrizCompleta[i][j].selecionado = false;
                if ($scope.model.matrizCompleta[i][j].comPalavra) {
                    $scope.model.matrizCompleta[i][j].style = {'background-color': $scope.model.matrizCompleta[i][j].comPalavra};
                } else {
                    $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
                }
            }
        }
    };
    
    var contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout(contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
        }
    };
    
    var barraDeProgresso = function () {
        var porcentagem = (perguntasTotaisEncontradas) / $scope.model.cacaPalavraLista.qntPergunta * 100;
        $scope.barraProgresso = {
            width: Math.round(porcentagem) + '%'
        };
    };
    
    $scope.passarParaProxMatriz = function () {
        perguntasTotaisEncontradas = 0;
        for (var i = 0; i <= indexMatriz; i++) {
            perguntasTotaisEncontradas = perguntasTotaisEncontradas + 
                    $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].perguntas.length;
        }
        barraDeProgresso();
        mudarMatriz();
    };
    
    var addRelatorioFinal = function () {
        if (indexMatriz >= 0) {
            for (var i = 0; i < $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].perguntas.length; i++) {
                var pergunta = $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].perguntas[i];
                var ganhou = pergunta.style?true:false;
                var respostaCorreta = pergunta.respostas[0];
                var resposta = ganhou?respostaCorreta:null;
                $scope.model.resultados.push({
                    'pulo': false,
                    'dica': posicaoDicaPergunta===i,
                    'tempoAcabou': false,
                    'pontuacao': ganhou?100:0,
                    'respostaEscolhida': resposta, 
                    'respostaCorreta': respostaCorreta, 
                    'ganhou':ganhou,
                    'pergunta': pergunta});
            }
        }
    };
    
    var mudarMatriz = function () {
        addRelatorioFinal();
        indexMatriz++;
        palavrasEncontradas = 0;
        if (indexMatriz < $scope.model.cacaPalavraLista.cacaPalavra.length) {
            $scope.model.matrizCompleta = $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].matriz;
            $scope.model.perguntas = $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].perguntas;
            $scope.model.anexosString = [];
            for (var i = 0; i < $scope.model.perguntas.length; i++) {
                $scope.model.anexosString.push(exibirAnexo($scope.model.perguntas[i].anexo));
            }
            tamanhoMatriz = $scope.model.cacaPalavraLista.cacaPalavra[indexMatriz].tamanhoMatriz;
            backgroundMatriz();
        } else if (idEtapaAluno) {
            $scope.model.anexoString = null;
            $scope.model.pergunta = null;
            var relatorioEtapa = new RelatorioEtapa();
            relatorioEtapa.etapaAluno = {'id': idEtapaAluno};
            relatorioEtapa.pontuacao = $scope.model.pontuacao;
            relatorioEtapa.idCursoAluno = idCursoAluno;
            relatorioEtapa.idAluno = $rootScope.usuarioLogado.id;
            relatorioEtapa.ganhou = ($scope.model.pontuacao >= pontuacaoMinima);
            if (relatorioEtapa.ganhou) {
                relatorioEtapa.perguntasEtapasAlunos = $scope.model.resultados;
            } else {
                relatorioEtapa.perguntasEtapasAlunos = [];
                for (var i = 0; i < $scope.model.resultados.length; i++) {
                    if ($scope.model.resultados[i].respostaEscolhida){
                        relatorioEtapa.perguntasEtapasAlunos.push($scope.model.resultados[i]);
                    }
                }
            }
            $rootScope.appLoaded = false;
            if ($scope.model.pontuacao < pontuacaoMinima) {
                $scope.model.perdeuJogo = true;
                tempoImagemFimDeJogo();
            } else {
                $scope.model.resultado = true;
                tempoImagemFimDeJogo();
            }
            relatorioEtapa.$save(function () {
                if ($scope.model.pontuacao < pontuacaoMinima) {
                    $scope.model.perdeuJogo = true;
                } else {
                    $scope.model.resultado = true;
                }
                tempoImagemFimDeJogo();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            if ($scope.model.pontuacao < pontuacaoMinima) {
                $scope.model.perdeuJogo = true;
            } else {
                $scope.model.resultado = true;
            }
            tempoImagemFimDeJogo();
        }
    };
    
    var exibirAnexo = function (anexo) {
        if (anexo && anexo.bytes) {
            return "data:image/"+"jpg"+";base64,"+anexo.bytes;
        }
        return null;
    };
    
    var tempoImagemFimDeJogo = function () {
        $scope.imagem1 = !$scope.imagem1;
        $timeout(tempoImagemFimDeJogo, 500);
    };
    
    var iniciarJogo = function (cacaPalavraLista) {
        $scope.model.cacaPalavraLista = cacaPalavraLista;
        var pontuacaoMaxima = cacaPalavraLista.qntPergunta*100;
        pontuacaoMinima = pontuacaoMaxima*7/10;
        mudarMatriz();
        barraDeProgresso();
        $rootScope.appLoaded = true;
        $rootScope.contagem = true;
        contagemInicial();
    };
    
    var init = function () {
        $scope.telaInit = false;
        $rootScope.appLoaded = false;
        if (idCursoAluno && idEtapa) {
            Jogo.buscarPerguntasDoJogoCacaPalavra({'idEtapa': idEtapa}).$promise.then(function (cacaPalavraLista) {
                iniciarJogo(cacaPalavraLista);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            Jogo.buscarPerguntasDaApresentacaoDoJogoCacaPalavra(function (cacaPalavraLista) {
                iniciarJogo(cacaPalavraLista);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);