tccApp.controller('JogoController', ['$scope', '$location', function ($scope, $location) {
        $scope.simularJogo = function (jogo) {
            $location.path("/jogos-simulado/"+jogo);
        };
}]);