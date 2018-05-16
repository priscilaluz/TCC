tccApp.controller('ExplicacaoJogoController', ['$scope', '$modalInstance', 'obj',
    function ($scope, $modalInstance, obj) {
        $scope.model = {
            url: "",
            htmlIntroducao: "partials/jogo/explicacao-introducao-:jogo.html",
            explicacao: null,
            pagina: 0,
            ultimaPagina: 0,
            jogo: obj.jogo.descricao
        };
        
        var urlPadrao = "img/jogos/explicacao/:jogo/:pagina.png";
        var jogoNome = "";
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
            if (pagina >= 0){
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
                jogoNome = "aposta";
                $scope.model.ultimaPagina = 8;
            }
            if (obj.jogo.id==="Q") {
                jogoNome = "quiz";
                $scope.model.ultimaPagina = 6;
            }
            if (obj.jogo.id==="F") {
                jogoNome = "forca";
                $scope.model.ultimaPagina = 7;
            }
            if (obj.jogo.id==="C") {
                jogoNome = "caca-palavra";
                $scope.model.ultimaPagina = 6;
            }
            if (obj.jogo.id==="V") {
                jogoNome = "velha";
                $scope.model.ultimaPagina = 1;
            }
            urlPadrao = urlPadrao.replace(":jogo", jogoNome);
            $scope.model.url = urlPadrao.replace(":pagina", $scope.model.pagina);
            
            $scope.model.htmlIntroducao = $scope.model.htmlIntroducao.replace(":jogo", jogoNome);
        };
        init();
    }]);