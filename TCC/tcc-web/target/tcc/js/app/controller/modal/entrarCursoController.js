tccApp.controller('EntrarCursoController', ['$scope', '$rootScope', '$modalInstance', '$location', 'Curso', 'CursoAluno', 'idCurso', 'growl',
    function ($scope, $rootScope, $modalInstance, $location, Curso, CursoAluno, idCurso, growl) {
        $scope.model = {
            idCurso: idCurso,
            curso: null
        };

        $scope.entrar = function () {
            $rootScope.appLoaded = false;
            CursoAluno.entrarCurso({'idCurso': $scope.model.idCurso, 'idAluno': $rootScope.usuarioLogado.id,
                'codAcesso': $scope.model.codAcesso}, function (cursoUsuario) {
                growl.success('Acesso ao curso concedido com sucesso.',{title: 'Operação bem sucedida'});
                $rootScope.appLoaded = true;
                $location.path("/aluno-cursando/"+cursoUsuario.id);
                $scope.fechar();
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