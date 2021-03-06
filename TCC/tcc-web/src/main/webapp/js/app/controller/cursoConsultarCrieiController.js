tccApp.controller('CursoConsultarCrieiController', ['$scope', '$rootScope', '$modal', 'Curso', 'Categoria', 'Enums', '$location', 'growl',
    function ($scope, $rootScope, $modal, Curso, Categoria, Enums, $location, growl) {
        $rootScope.telaHomeAluno = false;
        $scope.cursos = [];
        $scope.pesquisar = {};
        $scope.paginaAtual = null;
        $scope.paginacao = {paginaAtual: null, numDeItens: null, qntPaginaMostrarTela: null, qntPorPagina: null};

        $scope.pesquisarCurso = function (paginaAtual) {
            $rootScope.appLoaded = false;
            var idCategoria = $scope.pesquisar.categoria ? $scope.pesquisar.categoria.id : null;
            var idSituacao = $scope.pesquisar.situacao ? $scope.pesquisar.situacao.id : null;
            Curso.buscarCursos({'idUsuario': $scope.usuarioLogado.id, 'parteNome': $scope.pesquisar.descricao,
                'categoria': idCategoria, 'situacao':idSituacao, 'paginaAtual': paginaAtual-1}, function (result) {
                $scope.cursos = result.lista;
                $scope.paginacao = result.paginacao;
                $scope.paginaAtual = result.paginacao.paginaAtual+1;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.excluirCurso = function (curso) {
            var infor = {titulo:'Deseja realmente excluir curso?', campos: []};
            infor.campos.push({titulo: 'Nome:', descricao: curso.nome});
            infor.campos.push({titulo: 'Situação:', descricao: curso.situacao.descricao});
            infor.campos.push({titulo: 'Disponibilidade:', descricao: curso.disponibilidade.descricao});
            infor.campos.push({titulo: 'Categoria:', descricao: curso.categoria.nome});
            $modal.open({
                templateUrl: 'partials/confirmar-exclusao.html',
                controller: 'ConfirmarExclusaoController',
                size: 'md',
                resolve: {infor: function () {return infor;}}
            }).result.then(function (excluir) {
                if (excluir) {
                    $rootScope.appLoaded = false;
                    Curso.deletarCurso({'id': curso.id}).$promise.then(function (result) {
                        growl.success('Curso excluída com sucesso.',{title: 'Operação bem sucedida'});
                        $scope.pesquisarCurso();
                    }, function (error) {
                        $rootScope.appLoaded = true;
                    });
                }
            }, function () {
                // Modal cancelado
            });
        };
        
        $scope.telaNovaEditarCurso = function (idCurso, situacao) {
            var comId = "";
            if (idCurso) {
                comId = "/"+idCurso+"/"+situacao;
            }
            $location.path("/criar-curso"+comId);
        };
        
        $scope.copiarCurso = function (index) {
            var obj = {'nomeCurso': $scope.cursos[index].nome, 'idCurso': $scope.cursos[index].id, 'idUsuario': $scope.usuarioLogado.id};
            $modal.open({
                templateUrl: 'partials/curso/consultar-meus-professor/copiar-curso.html',
                controller: 'CopiarCursoController',
                size: 'lg',
                resolve: {obj: function () {return obj;}}
            }).result.then(function (cursoId) {
                $scope.telaNovaEditarCurso(cursoId, "R");
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