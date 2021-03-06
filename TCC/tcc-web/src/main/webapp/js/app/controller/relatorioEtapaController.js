tccApp.controller('RelatorioEtapaController', ['$scope', '$rootScope', '$routeParams', '$location', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, $location, CursoAluno) {
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            jogo: $routeParams.jogo
        };
        var idRelatorio = $routeParams.idRelatorio;
        var idCursoAluno = $routeParams.idCursoAluno;
        var idEtapa = $routeParams.idEtapa;
        var idEtapaAluno = $routeParams.idEtapaAluno;
        var aberto = $routeParams.aberto;
        
        $scope.voltar = function () {
            $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarRelatoriosEtapaPorId({'idRelatorio': idRelatorio}).$promise.then(function (relatorio) {
                $scope.model.pontuacao = relatorio.pontuacao;
                $scope.model.resultados = relatorio.perguntasEtapasAlunos;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);