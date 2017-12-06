tccApp.controller('ApostaController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    var tempoPadraoPergunta = 60;
    var pontuacaoInicial = 1000;
    var pontuacaoInicialFase = pontuacaoInicial;
    var timeoutTempoPorPergunta = null;
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var pontuacaoMinima = 0;
    $scope.imagem1 = true;
    $scope.model = {
        jogo:"Aposta",
        perdeuJogo: false,
        pulo: false,
        dica: false,
        barraPontos: {},
        tempo: tempoPadraoPergunta,
        maxFichas: null,
        pontuacao: pontuacaoInicial,
        valorAposta: [],
        imgAumentar: [],
        imgDiminuir: [],
        resultados: [],
        qntPulo:1,
        qntDica: 1,
        posicao: 0
    };
    
    $scope.voltar = function () {
        if (idCursoAluno && idEtapa){
            $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno);
        } else {
            $location.path("/jogos");
        }
    };
    
    $scope.apostou = function () {
        for (var i = 0; i < $scope.model.valorAposta.length; i++) {
            if ($scope.model.valorAposta[i] > 0){
                return true;
            }
        }
        return false;
    };
    
    $scope.apostar = function (tempoAcabou) {
        $timeout.cancel(timeoutTempoPorPergunta);
        var respostaCorreta;
        var apostasFase = angular.copy($scope.model.valorAposta);
        for (var i = 0; i < $scope.model.pergunta.respostas.length; i++) {
            if ($scope.model.pergunta.respostas[i].correta) {
                pontuacaoInicialFase = $scope.model.pontuacao + ($scope.model.valorAposta[i]*2);
                $scope.model.pontuacao = pontuacaoInicialFase;
                respostaCorreta = $scope.model.pergunta.respostas[i];
            }
        }
        if (tempoAcabou && !$scope.apostou()) {
            $scope.model.pontuacao = $scope.model.pontuacao - 200;
        }
        if ($scope.model.pulo) {
            $scope.model.pontuacao = $scope.model.pontuacao - 100;
        }
        if ($scope.model.pontuacao > 0) {
            $scope.respostaIncorreta = {
                'background-color': '#ec3e3e'
            };
            $scope.respostaCorreta = {
                'background-color': '#51ec3e'
            };

            $scope.model.resultados.push({'apostasFase': apostasFase, 'apostas': apostasFase.toString(),
                'respostaCorreta': respostaCorreta, 'pontuacao': $scope.model.pontuacao,
                'pergunta': $scope.model.pergunta, 'pulo':  $scope.model.pulo, 'dica':  $scope.model.dica, 'tempoAcabou': tempoAcabou});
            $scope.model.dica = false;
            $timeout(proximaPergunta, 400);
        } else {
            $scope.model.perdeuJogo = true;
        }
    };
    
    var proximaPergunta = function (){
        $scope.model.pulo = false;
        $scope.model.dica = false;
        $scope.respostaIncorreta = {};
        $scope.respostaCorreta = {};
        $scope.model.tempo = tempoPadraoPergunta+1;
        $scope.model.posicao++;
        atualizarPorcentagemBarra();
        barraDeProgresso();
        if (($scope.model.posicao) < $scope.model.perguntas.length) {
            $scope.model.pergunta = $scope.model.perguntas[$scope.model.posicao];
            $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
            inicializarBotao();
            tempoPergunta();
        } else {
            $timeout.cancel(timeoutTempoPorPergunta);
            $scope.model.pergunta = null;
            $scope.model.anexoString = null;
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
                    $scope.model.resultado = true;
                }
                if ($scope.model.pontuacao < pontuacaoMinima) {
                    $scope.model.perdeuJogo = true;
                    tempoImagemFimDeJogo();
                } else {
                    $scope.model.resultado = true;
                }
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };       
                
    $scope.diminuirAposta = function (index) {
        var pontuacaoDepois = $scope.model.pontuacao + 100;
        var pontuacaoLinhaDepois = $scope.model.valorAposta[index] - 100;
        if (pontuacaoDepois <= pontuacaoInicialFase && pontuacaoLinhaDepois >= 0){
            $scope.model.pontuacao = pontuacaoDepois;
            $scope.model.valorAposta[index] = pontuacaoLinhaDepois;
        }
    };
    
    $scope.aumentarAposta = function (index) {
        var pontuacaoDepois = $scope.model.pontuacao - 100;
        if (pontuacaoDepois >= 0){
            $scope.model.pontuacao = $scope.model.pontuacao - 100;
            $scope.model.valorAposta[index] = $scope.model.valorAposta[index] + 100;
        }
    };
    
    $scope.aumentarApostaTudo = function (index) {
        $scope.model.valorAposta[index] = $scope.model.valorAposta[index] + $scope.model.pontuacao;
        $scope.model.pontuacao = 0;
    };
    
    var tempoPergunta = function () {
        $scope.model.tempo--;
        if ($scope.model.tempo !== 0) {
            timeoutTempoPorPergunta = $timeout(tempoPergunta, 1000);
        } else {
            $scope.apostar(true);
        }
    };
    
    var contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout(contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
            tempoPergunta();
        }
    };
    
    var barraDeProgresso = function () {
        var porcentagem = ($scope.model.posicao+1)/$scope.model.perguntas.length*100;
        $scope.barraProgresso = {width: porcentagem+'%'};
    };

    var atualizarPorcentagemBarra = function () {
        var porcentagemAtual = 370 - ($scope.model.pontuacao*400/$scope.model.maxFichas);
        $scope.model.barraPontos = {'margin-top':porcentagemAtual};
    };
    
    var inicializarBotao = function () {
        $scope.model.valorAposta = [];
        $scope.model.imgAumentar = [];
        $scope.model.imgDiminuir = [];
        $scope.model.imgAumentarTudo = [];
        for (var i = 0; i < $scope.model.pergunta.respostas.length; i++) {
            $scope.model.valorAposta.push(0);
            $scope.model.imgAumentar.push("img/jogos/aposta/botaoAumentar-off.png");
            $scope.model.imgDiminuir.push("img/jogos/aposta/botaoDiminuir-off.png");
            $scope.model.imgAumentarTudo.push("img/jogos/aposta/tudo-off.png");
        }
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
            tempoPergunta();
        }, function () {
            // Modal cancelado
            tempoPergunta();
        });
    };
    
    $scope.pularPergunta = function () {
        $scope.model.pulo = true;
        $scope.model.qntPulo--;
        $scope.apostar(false);
    };
    
    var iniciarJogo = function (perguntas) {
        $scope.model.perguntas = perguntas;
        $scope.model.pergunta = perguntas[$scope.model.posicao];
        $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
        $scope.model.maxFichas = pontuacaoInicial * Math.pow(2, $scope.model.perguntas.length);
        pontuacaoMinima = $scope.model.maxFichas*5/10;
        $scope.telaInit = false;
        $rootScope.contagem = true;
        $scope.count = 0;
        inicializarBotao();
        atualizarPorcentagemBarra();
        barraDeProgresso();
        contagemInicial();
        $rootScope.appLoaded = true;
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
            Jogo.buscarPerguntaDaApresentacaoDoJogoAposta(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);