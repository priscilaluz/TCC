tccApp.controller('JogoController', ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {
        $scope.simularJogo = function (jogo) {
            $rootScope.telaHomeAluno = false;
            $location.path("/jogos-simulado/"+jogo);
        };
}]);