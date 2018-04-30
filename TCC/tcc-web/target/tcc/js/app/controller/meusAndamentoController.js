tccApp.controller('MeusAndamentoController', ['$scope', '$rootScope', 'CursoAluno',
    function ($scope, $rootScope, CursoAluno) {
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