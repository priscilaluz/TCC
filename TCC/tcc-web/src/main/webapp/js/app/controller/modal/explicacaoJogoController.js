tccApp.controller('ExplicacaoJogoController', ['$scope', '$modalInstance', 'obj',
    function ($scope, $modalInstance, obj) {
        $scope.model = {
            url: "",
            explicacao: null,
            pagina: 1,
            ultimaPagina: 1,
            jogo: obj.jogo.descricao
        };
        
        var urlPadrao = "img/jogos/explicacao/:jogo/:pagina.png";
        var pastaJogo = "";
        var jogo = obj.jogo;
        var idCursoAluno = obj.idCursoAluno;
        var idEtapa = obj.idEtapa;
        var idEtapaAluno = obj.idEtapaAluno;
        var aberto = obj.aberto;

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.jogar = function () {
            var urlJogo = "/jogos-simulado/"+jogo.id.toLowerCase()+"/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto;
            $modalInstance.close(urlJogo);
        };
        
        $scope.anterior = function () {
            var pagina = $scope.model.pagina - 1;
            if (pagina > 0){
                $scope.model.url = urlPadrao.replace(":pagina", pagina);
                $scope.model.pagina--;
            }
        };
        
        $scope.proxima = function () {
            var pagina = $scope.model.pagina + 1;
            if (pagina <= $scope.model.ultimaPagina){
                $scope.model.url = urlPadrao.replace(":pagina", pagina);
                $scope.model.pagina++;
            }
        };
        
        var init = function () {
            if (obj.jogo.id==="A") {
                pastaJogo = "aposta";
                $scope.model.ultimaPagina = 8;
            }
            if (obj.jogo.id==="Q") {
                pastaJogo = "quiz";
                $scope.model.ultimaPagina = 6;
            }
            if (obj.jogo.id==="F") {
                pastaJogo = "forca";
                $scope.model.ultimaPagina = 7;
            }
            if (obj.jogo.id==="C") {
                pastaJogo = "caca-palavra";
                $scope.model.ultimaPagina = 6;
            }
            urlPadrao = urlPadrao.replace(":jogo", pastaJogo);
            $scope.model.url = urlPadrao.replace(":pagina", $scope.model.pagina);
        };
        init();
    }]);