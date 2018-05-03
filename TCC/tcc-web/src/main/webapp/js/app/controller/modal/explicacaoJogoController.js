tccApp.controller('ExplicacaoJogoController', ['$scope', '$modalInstance', '$location', 'obj',
    function ($scope, $modalInstance, $location, obj) {
        $scope.model = {
            explicacao: null,
            jogo: obj.jogo.descricao
        };
        var jogo = obj.jogo;
        var idCursoAluno = obj.idCursoAluno;
        var idEtapa = obj.idEtapa;
        var idEtapaAluno = obj.idEtapaAluno;
        var aberto = obj.aberto;

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.comecar = function () {
            $location.path("/jogos-simulado/"+jogo.toLowerCase()+"/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto);
            $scope.fechar();
        };
        
        var init = function () {
        };
        init();
    }]);