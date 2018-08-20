tccApp.controller('CursoTodosConsultarController', ['$scope', '$rootScope', 'Curso', 'Enums', '$modal', '$location', 'Categoria',
    function ($scope, $rootScope, Curso, Enums, $modal, $location, Categoria) {
        $rootScope.telaHomeAluno = false;
        $scope.cursos = [];
        $scope.pesquisar = {};
        $scope.paginaAtual = null;
        $scope.paginacao = {paginaAtual: null, numDeItens: null, qntPaginaMostrarTela: null, qntPorPagina: null};

        $scope.pesquisarCurso = function (paginaAtual) {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            Curso.buscarCursosAluno({'idAluno': $rootScope.usuarioLogado.id, 'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria, 'paginaAtual': paginaAtual-1}, function (result) {
                $scope.cursos = result.lista;
                $scope.paginacao = result.paginacao;
                $scope.paginaAtual = result.paginacao.paginaAtual+1;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.estudarCurso = function (idCursoAluno) {
            $location.path("/aluno-cursando/"+idCursoAluno);
        };

        $scope.entrarNoCurso = function (idCurso) {
            $modal.open({
                templateUrl: 'partials/curso/consultar-todos-aluno/entrar-curso.html',
                controller: 'EntrarCursoController',
                size: 'lg',
                resolve: {idCurso: function () {return idCurso;}}
            }).result.then(function (result) {
                // Modal retorno
            }, function () {
                // Modal cancelado
            });
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Categoria.buscarTodasCategoria(function (categorias) {
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