tccApp.controller('CursoSalvarController', ['$scope', '$rootScope', '$routeParams', '$modal', 'growl', 'Enums', 'Curso', 'Etapa', 'Categoria', 'AnexoService',
    function ($scope, $rootScope, $routeParams, $modal, growl, Enums, Curso, Etapa, Categoria, AnexoService) {
        $scope.categorias = [];
        $scope.curso = new Curso();
        var idCurso = null;
        $scope.model = {
            anexo: null,
            pergunta: null
        };
        $scope.cursoCompleto = false;
        $scope.abaEtapa = false;
        $scope.numeroEtapa = 0;
        
        $scope.deletarEtapa = function (id) {
            $rootScope.appLoaded = false;
            Etapa.deletar({'idCurso': $scope.curso.id, 'idEtapa':id}).$promise.then(function (result) {
                growl.success('Etapa excluída com sucesso.',{title: 'Operação bem sucedida'});
                $scope.voltar();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.voltar = function () {
            $scope.removerArquivo();
            $scope.numeroEtapa = $scope.numeroEtapa - 1;
            if ($scope.numeroEtapa === 0) {
                $scope.abaEtapa = false;
                buscarCurso(idCurso);
            } else {
                buscarEtapaAtual($scope.numeroEtapa);
            }
        };
        
        var buscarEtapaAtual = function (numeroEtapa) {
            Etapa.buscar({'idCurso': $scope.curso.id, 'nivel': numeroEtapa}, function (result) {
                if (result) {
                    $scope.etapa = result;
                    $scope.etapa.nivel = numeroEtapa;
                    $scope.perguntasEtapa = [];
                    if ($scope.etapa.etapasPerguntas) {
                        for (var i = 0; i < $scope.etapa.etapasPerguntas.length; i++) {
                            $scope.perguntasEtapa.push($scope.etapa.etapasPerguntas[i].pergunta);
                        }
                    }
                    carregarCombos();
                } else {
                    novaEtapa(numeroEtapa);
                }
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.salvarCurso = function (situacao) {
            $scope.curso.usuario = $rootScope.usuarioLogado;
            if (situacao === 'R'){
                $scope.curso.situacao = {id: "R", descricao: "Rascunho"};
            } else {
                $scope.curso.situacao = {id: "C", descricao: "Concluído"};
            }
            $rootScope.appLoaded = false;
            $scope.curso.etapas = [];
            $scope.curso.$save(function () {
                $rootScope.appLoaded = true;
                if (situacao === 'R') {
                    $scope.numeroEtapa = $scope.numeroEtapa + 1;
                    buscarEtapaAtual($scope.numeroEtapa);
                    $scope.abaEtapa = true;
                } else {
                    buscarCursoConcluido($scope.curso.id);
                }
                idCurso = $scope.curso.id;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var novaEtapa = function (numeroEtapa) {
            $scope.etapa = new Etapa();
            $scope.numeroEtapa = numeroEtapa;
            $scope.etapa.nivel = $scope.numeroEtapa;
            $scope.perguntasEtapa = [];
            $scope.etapa.idCurso = $scope.curso.id;
        };
        
        var carregarCombos = function () {
            $rootScope.appLoaded = false;
            $scope.etapa.idCurso = $scope.curso.id;
            Enums.getJogos(function (result) {
                $scope.jogos = result;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var buscarCurso = function (id) {
            $rootScope.appLoaded = false;
            Curso.buscarCursoPorId({'idCurso': id}, function (result) {
                $scope.curso = result;
                $scope.cursoCompleto = $scope.curso.situacao.id==='C';
                $rootScope.appLoaded = true;
                if (carregarTela && !$scope.cursoCompleto) {
                    carregarEtapaIncial(result);
                }
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var buscarCursoConcluido = function (id) {
            $rootScope.appLoaded = false;
            Curso.buscarCursoCompletoPorId({'idCurso': id}, function (result) {
                $scope.curso = result;
                $scope.cursoCompleto = $scope.curso.situacao.id==='C';
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var carregarEtapaIncial = function (curso) {
            $scope.numeroEtapa = curso.ultimaEtapa;
            $scope.abaEtapa = (curso.ultimaEtapa && curso.ultimaEtapa > 0);
            carregarTela = false;
            if ($scope.abaEtapa) {
                buscarEtapaAtual($scope.numeroEtapa);
            }
        };
        
        $scope.salvarEtapa = function (proximaEtapa) {
            $rootScope.appLoaded = false;
            $scope.etapa.etapasPerguntas = [];
            for (var i = 0; i < $scope.perguntasEtapa.length; i++) {
                $scope.etapa.etapasPerguntas.push({'pergunta': $scope.perguntasEtapa[i]});
            }
            
            $scope.etapa.$save(function () {
                if (proximaEtapa) {
                    $scope.numeroEtapa = $scope.etapa.nivel+1;
                    buscarEtapaAtual($scope.numeroEtapa);
                } else {
                    $scope.salvarCurso('C');
                    $scope.cursoCompleto = true;
                }
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
            
        };
        
        $scope.excluirPergunta = function (index) {
            $scope.perguntasEtapa.splice(index, 1);
            definirPosicaoPerguntas();
        };
        
        $scope.addPergunta = function () {
            var obj = {idCategoria: $scope.curso.categoria.id, perguntasEtapa:$scope.perguntasEtapa, 
                idUsuario: $rootScope.usuarioLogado.id, jogo: $scope.etapa.jogo.id};
            $modal.open({
                templateUrl: 'partials/curso/salvar/add-perguntas.html',
                controller: 'AdicionarPerguntaController',
                size: 'lg',
                resolve: {obj: function () {return obj;}}
            }).result.then(function (result) {
                $scope.perguntasEtapa = result;
                definirPosicaoPerguntas();
            }, function () {
                // Modal cancelado
            });
        };
        
        var definirPosicaoPerguntas = function () {
            for (var i = 0; i < $scope.perguntasEtapa.length; i++) {
                $scope.perguntasEtapa[i].posicao = i+1;
            }
        };
        
        //=== === === === === === ANEXO === === === === === ===//
        $scope.removerArquivo = function () {
            document.getElementById('file').value = "";
            $scope.model.anexo = null;
            if ($scope.etapa) {
                $scope.etapa.anexo = null;
            }
            if ($scope.curso) {
                $scope.curso.anexo = null;
            }
        };
        
        $scope.$watch('model.anexo', function() {
            upload();
        });
        
        var upload = function() {
            if ($scope.model.anexo && $scope.model.anexo.size !== 0){
                $rootScope.appLoaded = false;
                var uploadUrl = "/tcc/rest/anexo/buscarAnexo";
                AnexoService.uploadFileToUrl($scope.model.anexo, uploadUrl, $scope.model.anexo.name, uploadSucess, uploadError);
            }
        };

        var uploadSucess = function(retorno) {
            if ($scope.abaEtapa) {
                $scope.etapa.anexo = retorno;
            } else {
                $scope.curso.anexo = retorno;
            }
            $rootScope.appLoaded = true;
        };

        var uploadError = function(erro) {
            if ($scope.abaEtapa) {
                $scope.etapa.anexo = null;
            } else {
                $scope.curso.anexo = null;
            }
            $rootScope.appLoaded = true;
        };
        
        $scope.exibirAnexo = function (anexo) {
            return "data:image/"+"jpg"+";base64"+anexo.bytes;
        };
        //=== === === === === === ANEXO === === === === === ===//
        
        $scope.dadosPergunta = function (pergunta) {
            $modal.open({
                templateUrl: 'partials/curso/salvar/dados-pergunta.html',
                controller: 'DadoPerguntaController',
                size: 'lg',
                resolve: {pergunta: function () {return pergunta;}}
            }).result.then(function (result) {
                // Modal retorno
            }, function () {
                // Modal cancelado
            });
        };
        
        var carregarTela = true;
        var init = function () {
            $rootScope.appLoaded = false;
            Categoria.buscarCategoriaPorFiltro(function (result) {
                $scope.categorias = result;
                $rootScope.appLoaded = true;
                if ($routeParams.idCurso) {
                    idCurso = $routeParams.idCurso;
                    if ($routeParams.situacao && $routeParams.situacao === 'C'){
                        buscarCursoConcluido(idCurso);
                    } else {
                        buscarCurso(idCurso);
                    }
                }
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);