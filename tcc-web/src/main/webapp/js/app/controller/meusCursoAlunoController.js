tccApp.controller('MeusCursoAlunoController', ['$scope', '$rootScope', '$location', 'CursoAluno',
    function ($scope, $rootScope, $location, CursoAluno) {
        $scope.model = {
            cursosAndamento: null,
            cursosConcluido: null,
            andamento: false,
            concluido: false
        };

        $scope.estudarCurso = function (idCursoAluno) {
            $location.path("/aluno-cursando/"+idCursoAluno);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarCursoAlunoPorAlunoSituacao({'idAluno': $rootScope.usuarioLogado.id, 'idSituacao': "A"}).$promise.then(function (andamento) {
                $scope.model.cursosAndamento = andamento;
                CursoAluno.buscarCursoAlunoPorAlunoSituacao({'idAluno': $rootScope.usuarioLogado.id, 'idSituacao': "C"}).$promise.then(function (concluido) {
                    $scope.model.cursosConcluido = concluido;
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