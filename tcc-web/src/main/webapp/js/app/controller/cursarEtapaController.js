tccApp.controller('CursarEtapaController', ['$scope', '$rootScope', '$routeParams', '$location', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, $location, CursoAluno) {
        $scope.model = {
            etapaAluno:null
        };
        var idCursoAluno = $routeParams.idCursoAluno;
        var idEtapa = $routeParams.idEtapa;
        
        $scope.jogar = function () {
            var jogo = $scope.model.etapaAluno.etapa.jogo.id.toLowerCase();
            $location.path("/jogos-simulado/"+jogo+"/"+idCursoAluno+"/"+idEtapa);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarEtapaAlunoPorCursoAlunoEEtapa({'idCursoAluno': idCursoAluno, 'idEtapa': idEtapa}).$promise.then(function (etapaAluno) {
                $scope.model.etapaAluno = etapaAluno;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);