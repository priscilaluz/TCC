tccApp.controller('CursarEtapaController', ['$scope', '$rootScope', '$routeParams', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, CursoAluno) {
        $scope.model = {
            etapaAluno:null
        };
        var idCursoAluno = $routeParams.idCursoAluno;
        var idEtapa = $routeParams.idEtapa;
        
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