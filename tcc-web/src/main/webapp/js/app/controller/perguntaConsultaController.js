tccApp.controller('PerguntaConsultaController', ['$scope', '$rootScope', 'Pergunta', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, Pergunta, Enums, $location, growl) {
        $scope.categorias = [];
        $scope.perguntas = [];
        $scope.tipos = [];
        $scope.niveis = [];
        $scope.pesquisar = {};

        $scope.pesquisarPergunta = function () {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            var idNivel = $scope.pesquisar.nivel ? $scope.pesquisar.nivel.id : null;
            var idTipo = $scope.pesquisar.tipo ? $scope.pesquisar.tipo.id : null;
            Pergunta.buscarPerguntas({'idUsuario': $scope.usuarioLogado.id,'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria,'nivel': idNivel,'tipo': idTipo}, function (result) {
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
            Enums.getCategorias(function (categorias) {
                $scope.categorias = categorias;
                
                Enums.getTiposPergunta(function (tipos) {
                    $scope.tipos = tipos;
                    
                    Enums.getNiveisPergunta(function (niveis) {
                        $scope.niveis = niveis;
                        $rootScope.appLoaded = true;
                    }, function (error) {
                        $rootScope.appLoaded = true;
                    });
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();

    }]);