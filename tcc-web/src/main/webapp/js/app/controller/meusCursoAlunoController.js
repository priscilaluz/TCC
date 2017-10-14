tccApp.controller('MeusCursoAlunoController', ['$scope', '$rootScope', 'Curso',
    function ($scope, $rootScope, Curso) {
        $scope.model = {
            cursosAndamento: null,
            cursosConcluido: null,
            andamento: false,
            concluido: false
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Curso.buscarCursoAlunoPorAlunoSituacao({'idAluno': $rootScope.usuarioLogado.id, 'idSituacao': "A"}).$promise.then(function (andamento) {
                $scope.model.cursosAndamento = andamento;
                Curso.buscarCursoAlunoPorAlunoSituacao({'idAluno': $rootScope.usuarioLogado.id, 'idSituacao': "C"}).$promise.then(function (concluido) {
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