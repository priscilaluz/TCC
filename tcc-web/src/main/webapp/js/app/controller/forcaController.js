tccApp.controller('ForcaController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    $rootScope.contagem = true;
    $scope.count = 0;
    $scope.model = {
        pontuacao: 0,
        posicao: 0,
        pergunta: null,
        perguntas: [],
        resultados: [],
        qntPulo:1,
        qntDica: 1,
        dica: false,
        resultado: false
    };
    
    $scope.voltar = function () {
        $location.path("/jogos");
    };
    
    $scope.dicaPergunta = function () {
        var dica = "Essa pergunta não tem dica.";
        if ($scope.model.pergunta.dica){
            dica = $scope.model.pergunta.dica;
            $scope.model.dica = true;
            $scope.model.qntDica--;
        }
        $modal.open({
            templateUrl: 'partials/jogo/dica.html',
            controller: 'DicaController',
            resolve: {dica: function () {return dica;}}
        }).result.then(function (result) {}, function () {});
    };
    
    $scope.pularPergunta = function () {
        $scope.model.pulo = true;
        $scope.model.qntPulo--;
        addAoRelatorioFinal(null, true, false);
    };
    
    var addAoRelatorioFinal = function (resposta, pulo, dica) {
        
    };
    
    $scope.contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout($scope.contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
        }
    };
    
    var barraDeProgresso = function () {
        var porcentagem = ($scope.model.posicao+1)/$scope.model.perguntas.length*100;
        $scope.barraProgresso = {
            width: porcentagem+'%'
        };
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