tccApp.controller('MinhasMedalhasController', ['$scope', '$rootScope', '$location', 'Premio',
    function ($scope, $rootScope, $location, Premio) {
        if (!$rootScope.isAluno) {
            $location.path("/home");
        }
        $rootScope.telaHomeAluno = false;
        $scope.model = {
            premios: [],
            nenhumPremio: true
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            Premio.buscarPremioPorIdAluno({'idAluno': $rootScope.usuarioLogado.id}).$promise.then(function (premio) {
                $scope.model.premio = premio;
                $scope.model.nenhumPremio = $scope.model.premio.qntForcaVencida===0 && $scope.model.premio.qntCacaPalavrasVencida===0 &&
                        $scope.model.premio.qntJogoDaVelhaVencida===0 && $scope.model.premio.qntApostaVencida===0 &&
                        $scope.model.premio.qntQuizVencidos===0 && $scope.model.premio.qntPergFacilCorreta===0 &&
                        $scope.model.premio.qntPergMediaCorreta===0 && $scope.model.premio.qntPergDificilCorreta===0 &&
                        $scope.model.premio.qntApostaTudoEmUmaPergVencida===0 && $scope.model.premio.qntEtapaVencida===0 &&
                        $scope.model.premio.qntEtapaSemPulo===0 && $scope.model.premio.qntEtapaSemDica===0 &&
                        $scope.model.premio.qntPontosAcumulados===0 && $scope.model.premio.qntCursoConcluido===0;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();

    }]);