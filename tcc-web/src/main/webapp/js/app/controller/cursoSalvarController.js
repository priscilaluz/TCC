tccApp.controller('CursoSalvarController', ['$scope', '$rootScope', 'growl', 'Enums', 'Curso', 'Etapa', 'Pergunta',
    function ($scope, $rootScope, growl, Enums, Curso, Etapa, Pergunta) {
        $scope.categorias = [];
        $scope.curso = new Curso();
        $scope.model = {
            pergunta: null
        };
        $scope.cursoCompleto = false;
        $scope.abaEtapa = false;
        $scope.numeroEtapa = 0;
        
        $scope.voltar = function () {
            $scope.numeroEtapa = $scope.numeroEtapa - 1;
            if ($scope.numeroEtapa === 0) {
                $scope.abaEtapa = false;
            } else {
                $scope.etapa.nivel = $scope.numeroEtapa;
                Etapa.buscar({'idCurso': $scope.curso.id, 'nivel': $scope.numeroEtapa}, function (result) {
                    $scope.etapa = result;
                    carregarCombos();
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };
        
        $scope.salvarRascunho = function () {
            $scope.curso.usuario = $rootScope.usuarioLogado;
            $scope.curso.situacao = {id: "R", descricao: "Rascunho"};
            $rootScope.appLoaded = false;
            $scope.curso.$save(function () {
                $rootScope.appLoaded = true;
                novaEtapa();
                carregarCombos();
                $scope.abaEtapa = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var novaEtapa = function () {
            $scope.etapa = new Etapa();
            $scope.numeroEtapa = $scope.numeroEtapa + 1;
            $scope.etapa.nivel = $scope.numeroEtapa;
            $scope.perguntasEtapa = [];
            $scope.etapa.curso = {id: $scope.curso.id};
        };
        
        var carregarCombos = function () {
            $rootScope.appLoaded = false;
            $scope.etapa.curso = $scope.curso;
            Enums.getJogos(function (result) {
                $scope.jogos = result;
                Pergunta.buscarPerguntas({'idUsuario': $scope.usuarioLogado.id, 'parteNome': null,
                    'categoria': $scope.curso.categoria.id}, function (result) {
                    $scope.perguntas = result;
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
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
                    novaEtapa();
                } else {
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
        
        
        //INICIO: Só p teste
//        $scope.abaEtapa = true;
//        $scope.curso = {id: 2, categoria:{id: 'P'}};
//        novaEtapa();
//        carregarCombos();
//        $scope.perguntasEtapa.push({id:'1'});
//        $scope.perguntasEtapa.push({id:'2'});
//        $scope.perguntasEtapa.push({id:'23'});
        //FIM: Só p teste

    }]);