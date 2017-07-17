tccApp.controller('QuizController', ['$scope', '$rootScope', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $location, $timeout, Jogo) {
    $rootScope.contagem = true;
    $scope.count = 0;
    var timeoutTempoPorPergunta = null;
    var tempoPergunta = 30;
    $scope.model = {
        pontuacao: 0,
        posicao: 0,
        tempo: tempoPergunta,
        pergunta: null,
        perguntas: [],
        resultados: [],
        qntPulo:1,
        pulo:false,
        qntDica: 1,
        dica: false,
        resultado: false
    };
    
    var barraDeProgresso = function () {
        var porcentagem = ($scope.model.posicao+1)/$scope.model.perguntas.length*100;
        $scope.barraProgresso = {
            width: porcentagem+'%'
        };
    };
    
    $scope.voltar = function () {
        $location.path("/jogos");
    };
    
    $scope.selecionarResposta = function(resposta) {
        $timeout.cancel(timeoutTempoPorPergunta);
        if (resposta.correta) {
            $scope.model.pontuacao = $scope.model.pontuacao+50;
        }
        $scope.idReposta = resposta.id;
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
        $scope.model.resultados.push({'respostaEscolhida': resposta, 'respostaCorreta': respostaCorreta,
            'pergunta': $scope.model.pergunta, 'pulo':  $scope.model.pulo, 'dica':  $scope.model.dica});
        $timeout(proximaPergunta, 400);
    };
    
    $scope.tempoPergunta = function () {
        $scope.model.tempo--;
        if ($scope.model.tempo !== 0) {
            timeoutTempoPorPergunta = $timeout($scope.tempoPergunta, 1000);
        } else {
            proximaPergunta();
        }
    };
    
    var proximaPergunta = function (){
        $scope.respostaIncorreta = {};
        $scope.respostaCorreta = {};
        $scope.model.dica = false;
        $scope.model.pulo = false;
        $scope.idReposta = null;
        $scope.model.tempo = tempoPergunta;
        $scope.model.posicao++;
        barraDeProgresso();
        if (($scope.model.posicao+1) < $scope.model.perguntas.length) {
            $scope.model.pergunta = $scope.model.perguntas[$scope.model.posicao];
            $scope.tempoPergunta();
        } else {
            $scope.model.pergunta = null;
            $timeout.cancel(timeoutTempoPorPergunta);
            $scope.model.resultado = true;
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
        
    };
    
    $scope.dicaPergunta = function () {
        
    };
    var init = function () {
        $rootScope.appLoaded = false;
        $scope.telaInit = true;
        Jogo.buscarPerguntaDaApresentacaoDoJogo(function (perguntas) {
            $scope.model.perguntas = perguntas;
            $scope.model.pergunta = perguntas[$scope.model.posicao];
            $rootScope.appLoaded = true;
            $scope.telaInit = false;
            barraDeProgresso();
            $scope.contagemInicial();
        }, function (error) {
            $rootScope.appLoaded = true;
        });
    };
    init();
}]);