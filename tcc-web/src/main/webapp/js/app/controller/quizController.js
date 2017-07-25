tccApp.controller('QuizController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
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
        
        addAoRelatorioFinal(resposta, false, $scope.model.dica);
    };
    
    $scope.tempoPergunta = function () {
        $scope.model.tempo--;
        if ($scope.model.tempo !== 0) {
            timeoutTempoPorPergunta = $timeout($scope.tempoPergunta, 1000);
        } else {
            addAoRelatorioFinal(null, false, false);
        }
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
        $scope.model.resultados.push({'respostaEscolhida': resposta, 'respostaCorreta': respostaCorreta,
            'pergunta': $scope.model.pergunta, 'pulo':  pulo, 'dica':  dica});
        $scope.model.dica = false;
        $timeout(proximaPergunta, 400);
    };
    
    $scope.pularPergunta = function () {
        $scope.model.pulo = true;
        $scope.model.qntPulo--;
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
    
    var init = function () {
        $rootScope.appLoaded = false;
        $scope.telaInit = true;
        Jogo.buscarPerguntaDaApresentacaoDoJogoQuiz(function (perguntas) {
            $scope.model.perguntas = perguntas;
            $scope.model.pergunta = perguntas[$scope.model.posicao];
            $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
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