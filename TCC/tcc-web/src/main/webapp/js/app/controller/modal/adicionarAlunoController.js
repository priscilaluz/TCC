tccApp.controller('AdicionarAlunoController', ['$scope', '$rootScope', '$modalInstance', 'obj', 'Usuario', 'Curso',
    function ($scope, $rootScope, $modalInstance, obj, Usuario, Curso) {
        $scope.pesquisar = {
            minhasAlunos: true,
            parteNome: null
        };
        $scope.model = {
            todos:false,
            alunos: []
        };
        var idCurso = obj.idCurso;

        $scope.selecionarTodas = function () {
            for (var i = 0; i < $scope.model.alunos.length; i++) {
                $scope.model.alunos[i].selecionar = $scope.model.todos;
            }
        };

        $scope.selecionar = function () {
            $scope.model.todos = true;
            for (var i = 0; i < $scope.model.alunos.length; i++) {
                if (!$scope.model.alunos[i].selecionar) {
                    $scope.model.todos = false;
                    break;
                }
            }
        };

        $scope.buscarAlunos = function () {
            $rootScope.appLoaded = false;
            Usuario.buscarAlunos({'nome': $scope.pesquisar.parteNome, 'idCurso': idCurso}, function (result) {
                $scope.model.alunos = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.adicionar = function () {
            $rootScope.appLoaded = false;
            var alunosId = [];
            for (var i = 0; i < $scope.model.alunos.length; i++) {
                if ($scope.model.alunos[i].selecionar){
                    alunosId.push($scope.model.alunos[i].id);
                }
            }
            Curso.addAlunosAoCurso({'idCurso': idCurso, 'idAlunos': alunosId.toString()}, function (result) {
                $scope.model.alunos = result;
                $rootScope.appLoaded = true;
                $modalInstance.close(alunosId);
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