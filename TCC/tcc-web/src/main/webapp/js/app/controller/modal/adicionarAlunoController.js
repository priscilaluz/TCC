tccApp.controller('AdicionarAlunoController', ['$scope', '$rootScope', '$modalInstance', 'obj', 'Usuario', 'Curso',
    function ($scope, $rootScope, $modalInstance, obj, Usuario, Curso) {
        $scope.paginaAtualAluno = null;
        $scope.paginacaoAluno = {paginaAtual: null, numDeItens: null, qntPaginaMostrarTela: null, qntPorPagina: null};
        
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

        $scope.buscarAlunos = function (paginaAtual) {
            $rootScope.appLoaded = false;
            Usuario.buscarAlunos({'nome': $scope.pesquisar.parteNome, 'idCurso': idCurso, 'paginaAtual': paginaAtual-1}, function (result) {
                $scope.model.alunos = result.lista;
                $scope.paginacaoAluno = result.paginacao;
                $scope.paginaAtualAluno = result.paginacao.paginaAtual+1;
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