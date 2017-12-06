tccApp.controller('CategoriaConsultaController', ['$scope', '$rootScope', '$location', 'Categoria', 'growl',
    function ($scope, $rootScope, $location, Categoria, growl) {
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            nomeCategoria: null,
            categoria: []
        };
        
        $scope.pesquisar = function () {
            $rootScope.appLoaded = false;
            Categoria.buscarCategoriaPorFiltro({'nome': $scope.model.nomeCategoria},function (result) {
                $scope.model.categorias = result;
                $rootScope.appLoaded = true;
            },function(error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.excluirCategoria = function (id) {
            $rootScope.appLoaded = false;
            Categoria.deletarCategoria({'idCategoria':id}).$promise.then(function (result) {
                growl.success('Categoria excluída com sucesso.',{title: 'Operação bem sucedida'});
                $scope.pesquisar();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
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