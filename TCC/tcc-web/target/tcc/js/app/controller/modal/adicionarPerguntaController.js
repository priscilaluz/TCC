tccApp.controller('AdicionarPerguntaController', ['$scope', '$rootScope', '$modalInstance', 'obj', 'Pergunta', 'Enums',
    function ($scope, $rootScope, $modalInstance, obj, Pergunta, Enums) {
        $scope.pesquisar = {
            minhasPerguntas: true,
            nivel: null,
            parteNome: null
        };
        $scope.model = {
            todos:false,
            perguntas: []
        };
        var perguntasEtapa = obj.perguntasEtapa;
        var idCategoria = obj.idCategoria;
        var idUsuario = obj.idUsuario;
        var jogo = obj.jogo;
        var tipoPergunta = null;

        $scope.selecionarTodas = function () {
            for (var i = 0; i < $scope.model.perguntas.length; i++) {
                $scope.model.perguntas[i].selecionar = $scope.model.todos;
            }
        };

        $scope.selecionar = function () {
            $scope.model.todos = true;
            for (var i = 0; i < $scope.model.perguntas.length; i++) {
                if (!$scope.model.perguntas[i].selecionar) {
                    $scope.model.todos = false;
                    break;
                }
            }
        };

        $scope.buscarPerguntas = function () {
            var idNivel = $scope.pesquisar.nivel ? $scope.pesquisar.nivel.id : null;
            var usuario = ($scope.pesquisar.minhasPerguntas)?idUsuario:null;
            Pergunta.buscarPerguntas({'idUsuario': usuario, 'parteNome': $scope.pesquisar.parteNome,
                'categoria': idCategoria, 'nivel': idNivel, 'tipo': tipoPergunta.id}, function (result) {
                $scope.model.perguntas = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.adicionar = function () {
            for (var i = 0; i < $scope.model.perguntas.length; i++) {
                if ($scope.model.perguntas[i].selecionar){
                    var existe = false;
                    for (var j = 0; j < perguntasEtapa.length; j++) {
                        if ($scope.model.perguntas[i].id === perguntasEtapa[j].id) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        var pergunta = $scope.model.perguntas[i];
                        pergunta.selecionar = null;
                        perguntasEtapa.push(pergunta);
                    }
                }
            }
            $modalInstance.close(perguntasEtapa);
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
            //buscar tipos de perguntas pelo jogo
            Enums.getNiveisPergunta(function (niveis) {
                $scope.niveis = niveis;
                Enums.getTiposPerguntaPorJogo({'idJogo': jogo}, function (tipo) {
                    tipoPergunta = tipo;
                    $rootScope.appLoaded = true;
                }, function (error) {$rootScope.appLoaded = true;});
            }, function (error) {$rootScope.appLoaded = true;});
        };
        init();
    }]);