tccApp.controller('CategoriaSalvarController', ['$scope', '$rootScope', '$routeParams', '$location', 'Categoria', 'growl',
    function ($scope, $rootScope, $routeParams, $location, Categoria, growl) {
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            categoria:  new Categoria()
        };
        var idCategoria = $routeParams.idCategoria;
        
        $scope.salvarCategoria = function () {
            $rootScope.appLoaded = false;
            $scope.model.categoria.$save(function () {
                growl.success('Cadastro realizado com sucesso.',{title: 'Sucesso'});
                $scope.voltarTela();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.voltarTela = function () {
            $location.path("/consultar-categoria");
        };
        
        var init = function () {
            if (idCategoria) {
                $rootScope.appLoaded = false;
                Categoria.buscarCategoriaPorId({'idCategoria': idCategoria}).$promise.then(function (categoria) {
                    $scope.model.categoria = categoria;
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };
        init();
    }]);