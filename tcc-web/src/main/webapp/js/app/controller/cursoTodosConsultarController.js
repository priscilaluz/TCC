tccApp.controller('CursoTodosConsultarController', ['$scope', '$rootScope', 'Curso', 'Enums', '$modal', 'growl',
    function ($scope, $rootScope, Curso, Enums, $modal, growl) {
        $scope.cursos = [];
        $scope.pesquisar = {};

        $scope.pesquisarCurso = function () {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            Curso.buscarCursosAluno({'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria}, function (result) {
                $scope.cursos = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.entrarNoCurso = function (idCurso) {
            $modal.open({
                templateUrl: 'partials/curso/consultar-todos-aluno/entrar-curso.html',
                controller: 'EntrarCursoController',
                resolve: {idCurso: function () {return idCurso;}}
            }).result.then(function (result) {
                // Modal retorno
            }, function () {
                // Modal cancelado
            });
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Enums.getCategorias(function (categorias) {
                $scope.categorias = categorias;
                
                Enums.getSituacoesCurso(function (situacoes) {
                    $scope.situacoes = situacoes;
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