tccApp.controller('CursoSalvarController', ['$scope', '$rootScope', '$routeParams', 'growl', 'Enums', 'Curso', 'Etapa', 'Pergunta', 'AnexoService',
    function ($scope, $rootScope, $routeParams, growl, Enums, Curso, Etapa, Pergunta, AnexoService) {
        $scope.categorias = [];
        $scope.curso = new Curso();
        $scope.model = {
            anexo: null
        }
        $scope.model = {
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
            $scope.curso.$save(function () {
                $rootScope.appLoaded = true;
                if (situacao === 'R') {
                    $scope.numeroEtapa = $scope.numeroEtapa + 1;
                    buscarEtapaAtual($scope.numeroEtapa);
                    $scope.abaEtapa = true;
                } else {
                    buscarCurso($scope.curso.id);
                }
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
                Pergunta.buscarPerguntas({'idUsuario': $scope.usuarioLogado.id, 'parteNome': null,
                    'categoria': $scope.curso.categoria.id}, function (result) {
                    $scope.perguntas = [];
                    if ($scope.perguntasEtapa && $scope.perguntasEtapa.length > 0) {
                        for (var i = 0; i < result.length; i++) {
                            var existe = false;
                            for (var j = 0; j < $scope.perguntasEtapa.length; j++) {
                                if (result[i].id == $scope.perguntasEtapa[j].id) {
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                $scope.perguntas.push(result[i]);
                            }
                        }
                    } else {
                        $scope.perguntas = result;
                    }
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
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
            }, function (error) {
                $rootScope.appLoaded = true;
            });
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
            $scope.perguntas.push($scope.perguntasEtapa[index]);
            $scope.perguntasEtapa.splice(index, 1);
        };
        
        $scope.addPergunta = function () {
            var posicao;
            for (var i = 0; i < $scope.perguntas.length; i++) {
                if ($scope.perguntas[i].id === $scope.model.pergunta.id) {
                    posicao = i;
                    break;
                }
            }
            $scope.perguntas.splice(posicao, 1);
            $scope.perguntasEtapa.push($scope.model.pergunta);
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
        //=== === === === === === ANEXO === === === === === ===//
        
        var init = function () {
            $rootScope.appLoaded = false;
            Enums.getCategorias(function (result) {
                $scope.categorias = result;
                $rootScope.appLoaded = true;
                if ($routeParams.idCurso) {
                    buscarCurso($routeParams.idCurso);
                }
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);