tccApp.controller('MinhasMedalhasController', ['$scope', '$rootScope', '$location', 'Premio',
    function ($scope, $rootScope, $location, Premio) {
        if (!$rootScope.isAluno) {
            $location.path("/home");
        }
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            premios: []
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            Premio.buscarPremioPorIdAluno({'idAluno': $rootScope.usuarioLogado.id}).$promise.then(function (premio) {
                $scope.model.premio = premio;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();

    }]);