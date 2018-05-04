tccApp.controller('CopiarCursoController', ['$scope', '$rootScope', '$modalInstance', 'Curso', 'obj', 'growl',
    function ($scope, $rootScope, $modalInstance, Curso, obj, growl) {
        $scope.nomeCurso = obj.nomeCurso;
        var idCurso = obj.idCurso;
        var idUsuario = obj.idUsuario;

        $scope.copiarCurso = function () {
            $rootScope.appLoaded = false;
            Curso.copiarCurso({'nomeCurso': $scope.nomeCurso, 'idCurso': idCurso, 'idUsuario': idUsuario}, function (curso) {
                growl.success('Curso copiado com sucesso.',{title: 'Operação bem sucedida'});
                $modalInstance.close(curso.id);
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
        };
        init();
    }]);