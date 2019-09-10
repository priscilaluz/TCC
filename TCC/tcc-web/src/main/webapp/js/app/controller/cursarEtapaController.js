tccApp.controller('CursarEtapaController', ['$scope', '$rootScope', '$routeParams', '$location', '$modal', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, $location, $modal, CursoAluno) {
        $scope.model = {
            etapaAluno: null,
            jogo: null,
            aberto: "N",
            relatorios:[]
        };
        $scope.idCursoAluno = $routeParams.idCursoAluno;
        $scope.idEtapa = $routeParams.idEtapa;
        $scope.idEtapaAluno = $routeParams.idEtapaAluno;
        $scope.cursoAberto = $routeParams.aberto==="S";
        $scope.model.aberto = $routeParams.aberto;
        var jogo;
        
        $scope.voltar = function () {
            $location.path("/aluno-cursando/"+$scope.idCursoAluno);
        };
        
        $scope.jogar = function () {
            var urlJogo = "/jogos-simulado/"+jogo.id.toLowerCase()+"/"+$scope.idCursoAluno
                    +"/"+$scope.idEtapa+"/"+$scope.idEtapaAluno+"/"+$scope.model.aberto;
            $location.path(urlJogo);
            /*var obj = {jogo: jogo,
                idCursoAluno: $scope.idCursoAluno, 
                idEtapa: $scope.idEtapa, 
                idEtapaAluno: $scope.idEtapaAluno,
                aberto: $scope.model.aberto};
            $modal.open({
                templateUrl: 'partials/jogo/explicacao-jogo.html',
                controller: 'ExplicacaoJogoController',
                size: 'lg',
                resolve: {obj: function () {return obj;}}
            }).result.then(function (urlJogo) {
                 $location.path(urlJogo);
            }, function () {
                // Modal cancelado
            });*/
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarEtapaAlunoPorCursoAlunoEEtapa({'idCursoAluno': $scope.idCursoAluno, 'idEtapa': $scope.idEtapa}).$promise.then(function (etapaAluno) {
                $scope.model.etapaAluno = etapaAluno;
                $scope.model.jogo = etapaAluno.etapa.jogo.id.toLowerCase();
                jogo = etapaAluno.etapa.jogo;
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