tccApp.controller('PerguntaConsultaController', ['$scope', '$rootScope', 'Pergunta', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, Pergunta, Enums, $location, growl) {
        $scope.categorias = [];
        $scope.perguntas = [];
        $scope.pesquisar = {};

        $scope.pesquisarPergunta = function () {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            Pergunta.buscarPerguntas({'idUsuario': $scope.usuarioLogado.id,
                'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria}, function (result) {
                $scope.perguntas = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.excluirPergunta = function (index) {
            $rootScope.appLoaded = false;
            Pergunta.deletarPergunta({'id': $scope.perguntas[index].id}).$promise.then(function (result) {
                growl.success('Pergunta excluída com sucesso.',{title: 'Operação bem sucedida'});
                $scope.pesquisarPergunta();
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.telaNovaEditarPergunta = function (idPergunta) {
            var comId = idPergunta?"/"+idPergunta:"";
            $location.path("/salvar-pergunta"+comId);
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Enums.getCategorias(function (result) {
                $scope.categorias = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();

    }]);