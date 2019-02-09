tccApp.controller('PerguntaConsultaController', ['$scope', '$rootScope', '$modal', 'Pergunta', 'Categoria', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, $modal, Pergunta, Categoria, Enums, $location, growl) {
        if (!$rootScope.isProfessor) {
            $location.path("/home");
        }
        $scope.paginaAtual = null;
        $scope.paginacao = {paginaAtual: null, numDeItens: null, qntPaginaMostrarTela: null, qntPorPagina: null};
        
        $rootScope.telaHomeAluno = false;
        $scope.categorias = [];
        $scope.perguntas = [];
        $scope.tipos = [];
        $scope.niveis = [];
        $scope.pesquisar = {'minhasPerguntas': false};

        $scope.pesquisarPergunta = function (paginaAtual) {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            var idNivel = $scope.pesquisar.nivel ? $scope.pesquisar.nivel.id : null;
            var idTipo = $scope.pesquisar.tipo ? $scope.pesquisar.tipo.id : null;
            var idUsuario = $scope.pesquisar.minhasPerguntas ? $scope.usuarioLogado.id : null;
            Pergunta.buscarPerguntas({'idUsuario': idUsuario,'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria,'nivel': idNivel,'tipo': idTipo, 'paginaAtual': paginaAtual-1}, function (result) {
                $scope.perguntas = result.lista;
                $scope.paginacao = result.paginacao;
                $scope.paginaAtual = result.paginacao.paginaAtual+1;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.excluirPergunta = function (pergunta) {
            var infor = {titulo:'Deseja realmente excluir pergunta?', campos: []};
            infor.campos.push({titulo: 'Descrição:', descricao: pergunta.descricao});
            infor.campos.push({titulo: 'Nível:', descricao: pergunta.nivel.descricao});
            infor.campos.push({titulo: 'Tipo:', descricao: pergunta.tipo.descricao});
            infor.campos.push({titulo: 'Categoria:', descricao: pergunta.categoria.nome});
            $modal.open({
                templateUrl: 'partials/confirmar-exclusao.html',
                controller: 'ConfirmarExclusaoController',
                size: 'md',
                resolve: {infor: function () {return infor;}}
            }).result.then(function (excluir) {
                if (excluir) {
                    $rootScope.appLoaded = false;
                    Pergunta.deletarPergunta({'id': pergunta.id}).$promise.then(function (result) {
                        growl.success('Pergunta excluída com sucesso.',{title: 'Operação bem sucedida'});
                        $scope.pesquisarPergunta();
                    }, function (error) {
                        $rootScope.appLoaded = true;
                    });
                }
            }, function () {
                // Modal cancelado
            });
        };
        
        $scope.telaNovaEditarPergunta = function (idPergunta) {
            var comId = idPergunta?"/"+idPergunta:"";
            $location.path("/salvar-pergunta"+comId);
        };

        var init = function () {
            $rootScope.appLoaded = false;
            Categoria.buscarTodasCategoria(function (categorias) {
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