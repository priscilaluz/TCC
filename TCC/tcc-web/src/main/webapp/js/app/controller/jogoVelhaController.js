tccApp.controller('JogoVelhaController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    $scope.model = {
        posicao: 0,
        perguntas: [],
        resultado: false
    };
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var aberto = $routeParams.aberto;
    
    $scope.contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout($scope.contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
            //Quando acabar a contagem começar o tempo de cada pergunta
        }
    };
    
    var exibirAnexo = function (anexo) {
        if (anexo && anexo.bytes) {
            return "data:image/"+"jpg"+";base64,"+anexo.bytes;
        }
        return null;
    };
    
    var iniciarJogo = function (perguntas) {
        $scope.model.perguntas = perguntas;
        $scope.model.pergunta = perguntas[$scope.model.posicao];
        $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
        $scope.telaInit = false;
        $rootScope.appLoaded = true;
        $scope.contagemInicial();
    };
    
    var init = function () {
        $scope.telaInit = false;
        $rootScope.appLoaded = false;
        if (idCursoAluno && idEtapa) {
            Jogo.buscarPerguntasDoJogoGerais({'idEtapa': idEtapa}).$promise.then(function (cacaPalavraLista) {
//                iniciarJogo(cacaPalavraLista);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            Jogo.buscarPerguntasDaApresentacaoDoDaVelhaApresentacao(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);