tccApp.controller('MeusAndamentoController', ['$scope', '$rootScope', '$location', 'CursoAluno',
    function ($scope, $rootScope, $location, CursoAluno) {
        if (!$rootScope.isAluno) {
            $location.path("/home");
        }
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            meusAndamentos: []
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarProprioAndamento({'idAluno': $rootScope.usuarioLogado.id}).$promise.then(function (meusAndamentos) {
                $scope.model.meusAndamentos = meusAndamentos;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();

    }]);