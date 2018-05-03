tccApp.controller('QuizController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    $rootScope.contagem = true;
    $scope.count = 0;
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var aberto = $routeParams.aberto;
    var timeoutTempoPorPergunta = null;
    var tempoPergunta = 30;
    var pontuacaoPorPergunta = 100;
    var pontuacaoMinima = 0;
    $scope.imagem1 = true;
    $scope.model = {
        jogo:"Quiz",
        pontuacao: 0,
        posicao: 0,
        tempo: tempoPergunta,
        pergunta: null,
        perguntas: [],
        resultados: [],
        qntPulo:1,
        qntDica: 1,
        perdeuJogo: false,
        dica: false,
        resultado: false
    };
    
    var barraDeProgresso = function () {
        var porcentagem = ($scope.model.posicao+1)/$scope.model.perguntas.length*100;
        $scope.barraProgresso = {
            width: Math.round(porcentagem)+'%'
        };
    };
    
    $scope.voltar = function () {
        if (idCursoAluno && idEtapa){
            $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto);
        } else {
            $location.path("/jogos");
        }
    };
    
    $scope.selecionarResposta = function(resposta) {
        $timeout.cancel(timeoutTempoPorPergunta);
        if (resposta.correta) {
            addPontuacao($scope.model.dica, 0.75);
        }
        
        addAoRelatorioFinal(resposta, false, $scope.model.dica);
    };
    
    var addPontuacao = function (partePonto, porcento) {
        var pontoPergunta = pontuacaoPorPergunta;
        if (partePonto) {
            pontoPergunta = pontuacaoPorPergunta * porcento;
        }
        $scope.model.pontuacao = $scope.model.pontuacao+pontoPergunta;
    };
    
    $scope.tempoPergunta = function () {
        $scope.model.tempo--;
        if ($scope.model.tempo !== 0) {
            timeoutTempoPorPergunta = $timeout($scope.tempoPergunta, 1000);
        } else {
            addAoRelatorioFinal(null, false, false);
        }
    };
    
    var addAoRelatorioFinal = function (resposta, pulo, dica) {
        $scope.idReposta = resposta?resposta.id: null;
        $scope.respostaIncorreta = {
            'background-color': '#ec3e3e'
        };
        $scope.respostaCorreta = {
            'background-color': '#51ec3e'
        };
        
        var respostaCorreta = null;
        for (var i = 0; i < $scope.model.pergunta.respostas.length; i++) {
            if ($scope.model.pergunta.respostas[i].correta) {
                respostaCorreta = $scope.model.pergunta.respostas[i];
                break;
            }
        }
        $scope.model.resultados.push({'respostaEscolhida': resposta, 'respostaCorreta': respostaCorreta, 'pontuacao': $scope.model.pontuacao,
            'pergunta': $scope.model.pergunta, 'pulo':  pulo, 'dica':  dica, 'tempoAcabou': resposta!==null});
        $scope.model.dica = false;
        $timeout(proximaPergunta, 400);
    };
    
    var proximaPergunta = function (){
        $scope.respostaIncorreta = {};
        $scope.respostaCorreta = {};
        $scope.idReposta = null;
        $scope.model.tempo = tempoPergunta;
        $scope.model.posicao++;
        barraDeProgresso();
        if (($scope.model.posicao) < $scope.model.perguntas.length) {
            $scope.model.pergunta = $scope.model.perguntas[$scope.model.posicao];
            $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
            $scope.tempoPergunta();
        } else if (idEtapaAluno) {
            $scope.model.anexoString = null;
            $scope.model.pergunta = null;
            var relatorioEtapa = new RelatorioEtapa();
            relatorioEtapa.etapaAluno = {'id': idEtapaAluno};
            relatorioEtapa.pontuacao = $scope.model.pontuacao;
            relatorioEtapa.perguntasEtapasAlunos = $scope.model.resultados;
            relatorioEtapa.idCursoAluno = idCursoAluno;
            relatorioEtapa.ganhou = ($scope.model.pontuacao >= pontuacaoMinima);
            $rootScope.appLoaded = false;
            relatorioEtapa.$save(function () {
                if ($scope.model.pontuacao < pontuacaoMinima) {
                    $scope.model.perdeuJogo = true;
                    tempoImagemFimDeJogo();
                } else {
                    $timeout.cancel(timeoutTempoPorPergunta);
                    $scope.model.resultado = true;
                }
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            if ($scope.model.pontuacao < pontuacaoMinima) {
                $scope.model.perdeuJogo = true;
                tempoImagemFimDeJogo();
            } else {
                $timeout.cancel(timeoutTempoPorPergunta);
                $scope.model.resultado = true;
            }
        }
    };
    
    $scope.contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout($scope.contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
            $scope.tempoPergunta();
            //Quando acabar a contagem começar o tempo de cada pergunta
        }
    };
    
    $scope.pularPergunta = function () {
        $scope.model.pulo = true;
        $scope.model.qntPulo--;
        addPontuacao($scope.model.pulo, 0.5);
        addAoRelatorioFinal(null, true, false);
    };
    
    $scope.dicaPergunta = function () {
        $timeout.cancel(timeoutTempoPorPergunta);
        var dica = "Essa pergunta não tem dica.";
        if ($scope.model.pergunta.dica){
            dica = $scope.model.pergunta.dica;
            $scope.model.dica = true;
            $scope.model.qntDica--;
        }
        var obj = {'dica': dica};
        $modal.open({
            templateUrl: 'partials/jogo/dica.html',
            controller: 'DicaController',
            resolve: {obj: function () {return obj;}}
        }).result.then(function (result) {
            // Modal retorno
            $scope.tempoPergunta();
        }, function () {
            // Modal cancelado
            $scope.tempoPergunta();
        });
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
    
    var iniciarJogo = function (perguntas) {
        $scope.model.perguntas = perguntas;
            $scope.model.pergunta = perguntas[$scope.model.posicao];
            var pontuacaoMaxima = perguntas.length*pontuacaoPorPergunta;
            pontuacaoMinima = pontuacaoMaxima*7/10;
            $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
            $rootScope.appLoaded = true;
            $scope.telaInit = false;
            barraDeProgresso();
            $scope.contagemInicial();
    };
    
    var init = function () {
        $rootScope.appLoaded = false;
        $scope.telaInit = true;
        if (idCursoAluno && idEtapa) {
            Jogo.buscarPerguntaDosJogosQuizForcaAposta({'idEtapa': idEtapa}).$promise.then(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            Jogo.buscarPerguntaDaApresentacaoDoJogoQuiz(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);