tccApp.controller('CursoConsultarCrieiController', ['$scope', '$rootScope', 'Curso', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, Curso, Enums, $location, growl) {
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
            Curso.deletarCurso({'id': $scope.cursos[index].id}).$promise.then(function (result) {
                growl.success('Curso excluída com sucesso.',{title: 'Operação bem sucedida'});
                $scope.pesquisarCurso();
            }, function (error) {
                $rootScope.appLoaded = 'ok';
            });
        };
        
        $scope.telaNovaEditarCurso = function (idCurso) {
            var comId = idCurso?"/"+idCurso:"";
            $location.path("/criar-curso"+comId);
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