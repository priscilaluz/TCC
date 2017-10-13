tccApp.controller('EntrarCursoController', ['$scope', '$rootScope', '$modalInstance', 'Curso', 'idCurso',
    function ($scope, $rootScope, $modalInstance, Curso, idCurso) {
        $scope.model = {
            idCurso: idCurso,
            curso: null
        };

        $scope.entrar = function () {
            $rootScope.appLoaded = false;
            Curso.entrarCurso({'idCurso': $scope.model.idCurso, 'idAluno': $rootScope.usuarioLogado.id,
                'codAcesso': $scope.model.codAcesso}, function (cursoUsuario) {
                
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            Curso.buscarCursoCompletoPorId({'idCurso': $scope.model.idCurso}, function (result) {
                $scope.model.curso = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);