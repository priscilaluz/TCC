tccApp.controller('ForcaController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    //$rootScope.contagem = true;
    var imgCampoVazio = "img/jogos/forca/Letras/CampoVazio.png";
    $scope.perdeu = false;
    $scope.count = 0;
    $scope.model = {
        pontuacao: 0,
        posicao: 0,
        pergunta: null,
        letrasEscolhidas: [],
        perguntas: [],
        resultados: [],
        qntPulo:1,
        qntDica: 1,
        dica: false,
        resultado: false
    };
    var erro = 1;
    $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca"+erro+".png";
    
    $scope.voltar = function () {
        $location.path("/jogos");
    };
    
    $scope.dicaPergunta = function () {
        var dica = "Essa pergunta não tem dica.";
        if ($scope.model.pergunta.dica){
            dica = $scope.model.pergunta.dica;
            $scope.model.dica = true;
            $scope.model.qntDica--;
        }
        $modal.open({
            templateUrl: 'partials/jogo/dica.html',
            controller: 'DicaController',
            resolve: {dica: function () {return dica;}}
        }).result.then(function (result) {}, function () {});
    };
    
    $scope.pularPergunta = function () {
        $scope.model.pulo = true;
        $scope.model.qntPulo--;
        addAoRelatorioFinal(null, true, false);
    };
    
    var addAoRelatorioFinal = function (resposta, pulo, dica) {
        
    };
    
    var buscarLetrasComAcentos = function (letra){
        var letrasA = ["A","Á","À","Â","Ã"];
        var letrasE = ["E","É","È","Ê"];
        var letrasI = ["I","Í","Ì","Î"];
        var letrasO = ["O","Ó","Ò","Õ","Ô"];
        var letrasU = ["U","Ú","Ù","Û"];
        if (letrasA.indexOf(letra) >= 0) {return letrasA;}
        if (letrasE.indexOf(letra) >= 0) {return letrasE;}
        if (letrasI.indexOf(letra) >= 0) {return letrasI;}
        if (letrasO.indexOf(letra) >= 0) {return letrasO;}
        if (letrasU.indexOf(letra) >= 0) {return letrasU;}
        return [letra];
    };
    
    var buscarImgLetrasComAcentos = function (letraAcento, letraSemAcento){
        var agudos = ["Á", "É", "Í, Ó", 'Ú'];
        var tils = ["Ã", "Õ"];
        var circunflexos = ["Â", "Ê", "Î", "Ô", "Û"];
        var graves = ["À", "È", "Ì", "Ò", "Ù"];
        
        if (agudos.indexOf(letraAcento) >= 0) {return letraSemAcento+"agudo";}
        if (tils.indexOf(letraAcento) >= 0) {return letraSemAcento+"til";}
        if (circunflexos.indexOf(letraAcento) >= 0) {return letraSemAcento+"circunflexo";}
        if (graves.indexOf(letraAcento) >= 0) {return letraSemAcento+"grave";}
        return letraAcento;
    };
    
    $scope.escolherLetra = function (letra) {
        var letrasAceitadas = buscarLetrasComAcentos(letra);
        $scope.model.letrasEscolhidas.push(letra);
        var contemLetra = false;
        for (var i = 0; i < $scope.model.letras.length; i++) {
            if (letrasAceitadas.indexOf($scope.model.letras[i].letra) >= 0) {
                var letraCerta = buscarImgLetrasComAcentos($scope.model.letras[i].letra, letra);
                $scope.model.letras[i].imagem = "img/jogos/forca/Letras/"+letraCerta+"-correto.png";
                $scope.model.letras[i].escolhida = true;
                contemLetra = true;
            }
        }
        if (!contemLetra) {
            erro++;
            if (erro < 8){
                $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca"+erro+".png";
            } else {
                $scope.perdeu = true;
            }
        }
    };
    
    $scope.contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout($scope.contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
        }
    };
    
    var barraDeProgresso = function () {
        var porcentagem = ($scope.model.posicao+1)/$scope.model.perguntas.length*100;
        $scope.barraProgresso = {
            width: porcentagem+'%'
        };
    };
    
    var inicializarLetras = function () {
        $scope.model.letras = $scope.model.pergunta.respostas[0].letras;
        for (var i = 0; i < $scope.model.letras.length; i++) {
            $scope.model.letras[i].imagem = imgCampoVazio;
            $scope.model.letras[i].escolhida = false;
        }
    };
    
    var init = function () {
        $rootScope.appLoaded = false;
        $scope.telaInit = true;
        Jogo.buscarPerguntaDaApresentacaoDoJogoForca(function (perguntas) {
            $scope.model.perguntas = perguntas;
            $scope.model.pergunta = perguntas[$scope.model.posicao];
            inicializarLetras();
            $rootScope.appLoaded = true;
            $scope.telaInit = false;
            barraDeProgresso();
            //$scope.contagemInicial();
        }, function (error) {
            $rootScope.appLoaded = true;
        });
    };
    init();
}]);