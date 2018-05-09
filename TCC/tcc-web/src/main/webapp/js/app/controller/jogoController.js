tccApp.controller('JogoController', ['$scope', '$rootScope', '$location', '$modal',
    function ($scope, $rootScope, $location, $modal) {
        $scope.simularJogoVelha = function () {
            $location.path("/jogos-simulado/jogovelha");
        };
        $scope.simularJogo = function (jogo) {
            var obj = {jogo: jogo,
                idCursoAluno: 0, 
                idEtapa: 0, 
                idEtapaAluno: 0,
                aberto: "S"};
            $modal.open({
                templateUrl: 'partials/jogo/explicacao-jogo.html',
                controller: 'ExplicacaoJogoController',
                size: 'lg',
                resolve: {obj: function () {return obj;}}
            }).result.then(function (urlJogo) {
                 $rootScope.telaHomeAluno = false;
                $location.path("/jogos-simulado/"+jogo.url);
            }, function () {
                // Modal cancelado
            });
        };
}]);