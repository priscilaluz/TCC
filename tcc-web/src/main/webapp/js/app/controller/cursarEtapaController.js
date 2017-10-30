tccApp.controller('CursarEtapaController', ['$scope', '$rootScope', '$routeParams', '$location', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, $location, CursoAluno) {
        $scope.model = {
            etapaAluno: null,
            jogo: null,
            relatorios:[]
        };
        $scope.idCursoAluno = $routeParams.idCursoAluno;
        $scope.idEtapa = $routeParams.idEtapa;
        $scope.idEtapaAluno = $routeParams.idEtapaAluno;
        
        $scope.voltar = function () {
            $location.path("/aluno-cursando/"+$scope.idCursoAluno);
        };
        
        $scope.jogar = function () {
            $location.path("/jogos-simulado/"+$scope.model.jogo+"/"+$scope.idCursoAluno+"/"+$scope.idEtapa+"/"+$scope.idEtapaAluno);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarEtapaAlunoPorCursoAlunoEEtapa({'idCursoAluno': $scope.idCursoAluno, 'idEtapa': $scope.idEtapa}).$promise.then(function (etapaAluno) {
                $scope.model.etapaAluno = etapaAluno;
                $scope.model.jogo = etapaAluno.etapa.jogo.id.toLowerCase();
                CursoAluno.buscarRelatoriosEtapaPorIdEtapaAluno({'idEtapaAluno': $scope.idEtapaAluno}).$promise.then(function (relatorios) {
                    $scope.model.relatorios = relatorios;
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);