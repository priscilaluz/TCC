tccApp.controller('CategoriaConsultaController', ['$scope', '$rootScope', '$modal', '$location', 'Categoria', 'growl',
    function ($scope, $rootScope, $modal, $location, Categoria, growl) {
        if (!$rootScope.isAdministrador) {
            $location.path("/home");
        }
        $rootScope.telaHomeAluno = false;
        $scope.paginaAtual = null;
        $scope.paginacao = {paginaAtual: null, numDeItens: null, qntPaginaMostrarTela: null, qntPorPagina: null};
        
        $scope.model = {
            nomeCategoria: null,
            categoria: []
        };
        
        $scope.pesquisar = function (paginaAtual) {
            $rootScope.appLoaded = false;
            Categoria.buscarCategoriaPorFiltro({'parteNome': $scope.model.nomeCategoria, 'paginaAtual': paginaAtual-1},function (result) {
                $scope.model.categorias = result.lista;
                $scope.paginacao = result.paginacao;
                $scope.paginaAtual = result.paginacao.paginaAtual+1;
                $rootScope.appLoaded = true;
            },function(error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.excluirCategoria = function (categoria) {
            var infor = {titulo:'Deseja realmente excluir categoria?', campos: []};
            infor.campos.push({titulo: 'Nome:', descricao: categoria.nome});
            $modal.open({
                templateUrl: 'partials/confirmar-exclusao.html',
                controller: 'ConfirmarExclusaoController',
                size: 'md',
                resolve: {infor: function () {return infor;}}
            }).result.then(function (excluir) {
                if (excluir) {
                    $rootScope.appLoaded = false;
                    Categoria.deletarCategoria({'idCategoria':categoria.id}).$promise.then(function (result) {
                        growl.success('Categoria excluída com sucesso.',{title: 'Operação bem sucedida'});
                        $scope.pesquisar(1);
                        $rootScope.appLoaded = true;
                    }, function (error) {
                        $rootScope.appLoaded = true;
                    });
                }
            }, function () {
                // Modal cancelado
            });
        };
        
        $scope.telaNovaEditar = function (idProfessor) {
            var comId = idProfessor?"/"+idProfessor:"";
            $location.path("/salvar-categoria"+comId);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            $rootScope.appLoaded = true;
        };
        init();
    }]);