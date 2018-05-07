tccApp.controller('JogoDaVelhaController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var aberto = $routeParams.aberto;
    
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
                $scope.perguntas = perguntas;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);