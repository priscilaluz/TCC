tccApp.controller('CursoConsultarCrieiController', ['$scope', '$rootScope', 'Curso', 'Categoria', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, Curso, Categoria, Enums, $location, growl) {
        $scope.cursos = [];
        $scope.pesquisar = {};

        $scope.pesquisarCurso = function () {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            var idSituacao = $scope.pesquisar.situacao ? $scope.pesquisar.situacao.id : null;
            Curso.buscarCursos({'idUsuario': $scope.usuarioLogado.id,
                'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria, 'situacao':idSituacao}, function (result) {
                $scope.cursos = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.excluirCurso = function (index) {
            $rootScope.appLoaded = false;
            Curso.deletarCurso({'id': $scope.cursos[index].id}).$promise.then(function (result) {
                growl.success('Curso excluída com sucesso.',{title: 'Operação bem sucedida'});
                $scope.pesquisarCurso();
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.telaNovaEditarCurso = function (idCurso, situacao) {
            var comId = "";
            if (idCurso) {
                comId = "/"+idCurso+"/"+situacao;
            }
            $location.path("/criar-curso"+comId);
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Categoria.buscarCategoriaPorFiltro(function (categorias) {
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