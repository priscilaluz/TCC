tccApp.controller('ApostaController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    var tempoPadraoPergunta = 60;
    var pontuacaoInicial = 1000;
    var pontuacaoInicialFase = pontuacaoInicial;
    var timeoutTempoPorPergunta = null;
    $scope.model = {
        barraPontos: {},
        tempo: tempoPadraoPergunta,
        maxFichas: null,
        pontuacao: pontuacaoInicial,
        valorAposta: [],
        imgAumentar: [],
        imgDiminuir: [],
        qntPulo:1,
        qntDica: 1,
        posicao: 0
    };
    
    $scope.diminuirAposta = function (index) {
        var pontuacaoDepois = $scope.model.pontuacao + 100;
        if (pontuacaoDepois <= pontuacaoInicialFase){
            $scope.model.pontuacao = pontuacaoDepois;
            $scope.model.valorAposta[index] = $scope.model.valorAposta[index] - 100;
        }
    };
    
    $scope.aumentarAposta = function (index) {
        var pontuacaoDepois = $scope.model.pontuacao - 100;
        if (pontuacaoDepois >= 0){
            $scope.model.pontuacao = $scope.model.pontuacao - 100;
            $scope.model.valorAposta[index] = $scope.model.valorAposta[index] + 100;
        }
    };
    
    var tempoPergunta = function () {
        $scope.model.tempo--;
        if ($scope.model.tempo !== 0) {
            timeoutTempoPorPergunta = $timeout(tempoPergunta, 1000);
        } else {
            addAoRelatorioFinal(null, false, false);
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
    
//    $scope.aumentarPontuacao = function () {
//        $scope.model.pontuacao = $scope.model.pontuacao*2;
//        atualizarPorcentagemBarra();
//    };
    var atualizarPorcentagemBarra = function () {
        var porcentagemAtual = 370 - ($scope.model.pontuacao*400/$scope.model.maxFichas);
        $scope.model.barraPontos = {'margin-top':porcentagemAtual};
    };
    
    var inicializarBotao = function () {
        $scope.model.valorAposta = [];
        $scope.model.imgAumentar = [];
        $scope.model.imgDiminuir = [];
        for (var i = 0; i < $scope.model.pergunta.respostas.length; i++) {
            $scope.model.valorAposta.push(0);
            $scope.model.imgAumentar.push("img/jogos/aposta/botaoAumentar-off.png");
            $scope.model.imgDiminuir.push("img/jogos/aposta/botaoDiminuir-off.png");
        }
    };
    
    var init = function () {
        $rootScope.appLoaded = false;
        $scope.telaInit = true;
        Jogo.buscarPerguntaDaApresentacaoDoJogoAposta(function (perguntas) {
            $scope.model.perguntas = perguntas;
            $scope.model.pergunta = perguntas[$scope.model.posicao];
            $scope.model.maxFichas = pontuacaoInicial * Math.pow(2, $scope.model.perguntas.length);
            $rootScope.appLoaded = true;
            $scope.telaInit = false;
            inicializarBotao();
            atualizarPorcentagemBarra();
            barraDeProgresso();
            contagemInicial();
        }, function (error) {
            $rootScope.appLoaded = true;
        });
    };
    init();
}]);