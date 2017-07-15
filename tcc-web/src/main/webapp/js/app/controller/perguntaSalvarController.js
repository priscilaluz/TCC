tccApp.controller('PerguntaSalvarController', ['$scope', '$rootScope', '$routeParams', 'growl', 'Pergunta', 'Enums', 'AnexoService', '$location',
    function ($scope, $rootScope, $routeParams, growl, Pergunta, Enums, AnexoService, $location) {
        $scope.pergunta = new Pergunta();
        $scope.pergunta.respostas = [];
        $scope.telaCadastro = false;
        $scope.anexo = null;
        $scope.respostaUnica = null;
        $scope.opcao = null;
        $scope.categorias = [];

        $scope.addOpcao = function () {
            for (var i = 0; i < $scope.pergunta.respostas.length; i++) {
                if ($scope.pergunta.respostas[i].descricao === $scope.opcao) {
                    growl.error('Opção já adicionada', {title: 'Informação'});
                    return;
                }
            }
            $scope.pergunta.respostas.push({'descricao': $scope.opcao, 'correta': false});
            $scope.opcao = null;
        };

        $scope.excluirOpcao = function (index) {
            $scope.pergunta.respostas.splice(index, 1);
        };

        $scope.salvarPergunta = function () {
            if ($scope.respostaUnica && $scope.pergunta.tipo.id==='CL'){
                $scope.pergunta.respostas = [];
                $scope.pergunta.respostas.push({'descricao': $scope.respostaUnica, 'correta': true});
            }
            $scope.pergunta.usuario = $rootScope.usuarioLogado;
            $rootScope.appLoaded = false;
            $scope.pergunta.$save(function () {
                growl.success('Pergunta salva com sucesso.', {title: 'Sucesso'});
                $scope.voltarConsulta();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.changeTipo = function () {
            if ($scope.pergunta.tipo.id === 'ME') {
                $scope.respostaUnica = null;
            }
            if ($scope.pergunta.tipo.id === 'CL') {
                $scope.opcao = null;
                $scope.pergunta.respostas = [];
            }
        };

        $scope.removerArquivo = function () {
            $scope.anexo = null;
            $scope.pergunta.anexo = null;
        };
        
        $scope.voltarConsulta = function () {
            $location.path("/consultar-pergunta");
        };
        
        $scope.$watch('anexo', function() {
            upload();
        });
        
        var upload = function() {
            if ($scope.anexo && $scope.anexo.size !== 0){
                $rootScope.appLoaded = false;
                var uploadUrl = "/tcc/rest/anexo/buscarAnexo";
                AnexoService.uploadFileToUrl($scope.anexo, uploadUrl, $scope.anexo.name, uploadSucess, uploadError);
            }
        };

        var uploadSucess = function(retorno) {
            $scope.pergunta.anexo = retorno;
            $rootScope.appLoaded = true;
        };

        var uploadError = function(erro) {
            $scope.pergunta.anexo = null;
            $rootScope.appLoaded = true;
        };

        var buscarPerguntaPorId = function (id) {
            $rootScope.appLoaded = false;
            Pergunta.buscarPerguntaPorId({'idPergunta': id}).$promise.then(function (pergunta) {
                $scope.pergunta = pergunta;
                if ($scope.pergunta.tipo.id === 'CL' && $scope.pergunta.respostas && $scope.pergunta.respostas.length > 0){
                    $scope.respostaUnica = $scope.pergunta.respostas[0].descricao;
                    $scope.pergunta.respostas = [];
                }
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
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
                        if ($routeParams.idPergunta) {
                            buscarPerguntaPorId($routeParams.idPergunta);
                        }
                    }, function (error) {$rootScope.appLoaded = true;});
                }, function (error) {$rootScope.appLoaded = true;});
            }, function (error) {$rootScope.appLoaded = true;});
        };
        init();

    }]);