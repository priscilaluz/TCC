tccApp.controller('DadoPerguntaController', ['$scope', '$rootScope', '$modalInstance', 'pergunta', 'Pergunta',
    function ($scope, $rootScope, $modalInstance, pergunta, Pergunta) {
        $scope.model = {
            pergunta: null
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            Pergunta.buscarPerguntaPorId({'idPergunta': pergunta.id}).$promise.then(function (result) {
                $scope.model.pergunta = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);