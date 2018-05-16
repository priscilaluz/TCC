tccApp.controller('JogoVelhaController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
    $rootScope.telaHomeAluno = false;
    $scope.model = {
        matriz: [[{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}], 
                [{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}],
                [{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}]],
        pontuacao: 0,
        posicao: 0,
        qntVitoriasAtuais: 0,
        qntVitoriasNecessaria: 1,
        venceuJogoAtual: false,
        perdeuJogoAtual: false,
        perguntas: [],
        resultados: [],
        venceuJogo: false,
        dica: false,
        resultado: false
    };
    
    var idCursoAluno = $routeParams.idCursoAluno;
    var idEtapa = $routeParams.idEtapa;
    var idEtapaAluno = $routeParams.idEtapaAluno;
    var aberto = $routeParams.aberto;
    
    $scope.voltar = function () {
        if (idCursoAluno && idEtapa){
            $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno+"/"+aberto);
        } else {
            $location.path("/jogos");
        }
    };
    
    $scope.selecionarResposta = function(resposta) {
        if (resposta.correta) {
            $scope.model.pontuacao = $scope.model.pontuacao + 100;
            $scope.model.matriz[$scope.posicaoX][$scope.posicaoY].valor = "O";
        } else {
            $scope.model.matriz[$scope.posicaoX][$scope.posicaoY].valor = "X";
        }
        
        $scope.idReposta = resposta?resposta.id: null;
        $scope.respostaIncorreta = {'background-color': '#ec3e3e'};
        $scope.respostaCorreta = {'background-color': '#51ec3e'};
        
        var respostaCorreta = null;
        for (var i = 0; i < $scope.model.pergunta.respostas.length; i++) {
            if ($scope.model.pergunta.respostas[i].correta) {
                respostaCorreta = $scope.model.pergunta.respostas[i];
                break;
            }
        }
        $scope.model.resultados.push({'respostaEscolhida': resposta, 'respostaCorreta': respostaCorreta, 'pontuacao': resposta.correta?100:0,
            'pergunta': $scope.model.pergunta, 'dica':  $scope.model.dica});
        $scope.model.dica = false;
        $timeout(verificarVitoriaTimeOut, 400);
    };
    
    var verificarVitoriaTimeOut = function () {
        var verificar = vencerPerderContinuar();
        if (verificar !== "C") {
            $scope.model.qntVitoriasAtuais = (verificar==="V") ? ($scope.model.qntVitoriasAtuais+1) : $scope.model.qntVitoriasAtuais;
            $scope.model.venceuJogoAtual = (verificar==="V");
            $scope.model.perdeuJogoAtual = (verificar==="P");
        }
        
        if ($scope.model.venceuJogoAtual || $scope.model.perdeuJogoAtual) {
            $timeout(proximaPergunta, 1000);
        } else {
            proximaPergunta(true);
        }
    };
    
    var vencerPerderContinuar = function() {
        //Vertical
        if ($scope.model.matriz[0][0].valor && $scope.model.matriz[0][0].valor===$scope.model.matriz[1][0].valor && 
                $scope.model.matriz[1][0].valor===$scope.model.matriz[2][0].valor) {
            var retorno = $scope.model.matriz[0][0].valor==="O"?"V":"P";
            $scope.model.matriz[0][0].cor = retorno;
            $scope.model.matriz[1][0].cor = retorno;
            $scope.model.matriz[2][0].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[0][1].valor && $scope.model.matriz[0][1].valor===$scope.model.matriz[1][1].valor && 
                $scope.model.matriz[1][1].valor===$scope.model.matriz[2][1].valor) {
            var retorno = $scope.model.matriz[0][1].valor==="O"?"V":"P";
            $scope.model.matriz[0][1].cor = retorno;
            $scope.model.matriz[1][1].cor = retorno;
            $scope.model.matriz[2][1].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[0][2].valor && $scope.model.matriz[0][2].valor===$scope.model.matriz[1][2].valor && 
                $scope.model.matriz[1][2].valor===$scope.model.matriz[2][2].valor) {
            var retorno = $scope.model.matriz[2][0].valor==="O"?"V":"P";
            $scope.model.matriz[0][2].cor = retorno;
            $scope.model.matriz[1][2].cor = retorno;
            $scope.model.matriz[2][2].cor = retorno;
            return retorno;
        }
        //Horizontal
        if ($scope.model.matriz[0][0].valor && $scope.model.matriz[0][0].valor===$scope.model.matriz[0][1].valor &&
                $scope.model.matriz[0][1].valor===$scope.model.matriz[0][2].valor) {
            var retorno = $scope.model.matriz[0][0].valor==="O"?"V":"P";
            $scope.model.matriz[0][0].cor = retorno;
            $scope.model.matriz[0][1].cor = retorno;
            $scope.model.matriz[0][2].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[1][0].valor && $scope.model.matriz[1][0].valor===$scope.model.matriz[1][1].valor &&
                $scope.model.matriz[1][1].valor===$scope.model.matriz[1][2].valor) {
            var retorno = $scope.model.matriz[1][0].valor==="O"?"V":"P";
            $scope.model.matriz[1][0].cor = retorno;
            $scope.model.matriz[1][1].cor = retorno;
            $scope.model.matriz[1][2].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[2][0].valor && $scope.model.matriz[2][0].valor===$scope.model.matriz[2][1].valor &&
                $scope.model.matriz[2][1].valor===$scope.model.matriz[2][2].valor) {
            var retorno = $scope.model.matriz[2][0].valor==="O"?"V":"P";
            $scope.model.matriz[2][0].cor = retorno;
            $scope.model.matriz[2][1].cor = retorno;
            $scope.model.matriz[2][2].cor = retorno;
            return retorno;
        }
        //Diagonal
        if ($scope.model.matriz[0][0].valor && $scope.model.matriz[0][0].valor===$scope.model.matriz[1][1].valor &&
                $scope.model.matriz[1][1].valor===$scope.model.matriz[2][2].valor) {
            var retorno = $scope.model.matriz[0][0].valor==="O"?"V":"P";
            $scope.model.matriz[0][0].cor = retorno;
            $scope.model.matriz[1][1].cor = retorno;
            $scope.model.matriz[2][2].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[0][2].valor && $scope.model.matriz[0][2].valor===$scope.model.matriz[1][1].valor &&
                $scope.model.matriz[1][1].valor===$scope.model.matriz[2][0].valor) {
            var retorno = $scope.model.matriz[0][2].valor==="O"?"V":"P";
            $scope.model.matriz[0][2].cor = retorno;
            $scope.model.matriz[1][1].cor = retorno;
            $scope.model.matriz[0][2].cor = retorno;
            return retorno;
        }
        if ($scope.model.matriz[0][0].valor && $scope.model.matriz[1][0].valor && $scope.model.matriz[2][0].valor &&
            $scope.model.matriz[0][1].valor && $scope.model.matriz[1][1].valor && $scope.model.matriz[2][1].valor &&
            $scope.model.matriz[0][2].valor && $scope.model.matriz[1][2].valor && $scope.model.matriz[2][2].valor) {
            return "P";
        }
        return "C";
    };
    
    var proximaPergunta = function (manter) {
        if (!manter) {
            $scope.model.matriz = [[{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}], 
                                   [{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}],
                                   [{valor: null, cor:null}, {valor: null, cor:null}, {valor: null, cor:null}]];
        }
        $scope.model.venceuJogoAtual = false;
        $scope.model.perdeuJogoAtual = false;
        $scope.mostrarPergunta = false;
        $scope.respostaIncorreta = {};
        $scope.respostaCorreta = {};
        $scope.model.posicao++;
        if (($scope.model.posicao) < $scope.model.perguntas.length) {
            $scope.model.pergunta = $scope.model.perguntas[$scope.model.posicao];
            $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
        } else {
            final();
        }
    };
    
    var final = function () {
        var venceu = $scope.model.qntVitoriasAtuais >= $scope.model.qntVitoriasNecessaria;
        if (idEtapaAluno) {
            $scope.model.anexoString = null;
            $scope.model.pergunta = null;
            var relatorioEtapa = new RelatorioEtapa();
            relatorioEtapa.etapaAluno = {'id': idEtapaAluno};
            relatorioEtapa.pontuacao = $scope.model.pontuacao;
            relatorioEtapa.perguntasEtapasAlunos = $scope.model.resultados;
            relatorioEtapa.idCursoAluno = idCursoAluno;
            relatorioEtapa.ganhou = venceu;
            $rootScope.appLoaded = false;
            relatorioEtapa.$save(function () {
                $scope.model.venceuJogo = venceu;
                $scope.model.perdeuJogo = $scope.model.qntVitoriasAtuais < $scope.model.qntVitoriasNecessaria;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            $scope.model.venceuJogo = venceu;
            $scope.model.perdeuJogo = $scope.model.qntVitoriasAtuais < $scope.model.qntVitoriasNecessaria;
        }
    };
    
    $scope.fazerJogada = function (x, y) {
        if (!$scope.model.matriz[x][y].valor && !$scope.mostrarPergunta){
            $scope.posicaoX = x;
            $scope.posicaoY = y;
            $scope.mostrarPergunta = true;
            $scope.model.matriz[x][y].valor = "...";
        }
    };
    
    $scope.contagemInicial = function () {
        $scope.count++;
        if ($scope.count < 5) {
            $timeout($scope.contagemInicial, 1000);
        } else {
            $rootScope.contagem = false;
            //Quando acabar a contagem começar o tempo de cada pergunta
        }
    };
    
    var exibirAnexo = function (anexo) {
        if (anexo && anexo.bytes) {
            return "data:image/"+"jpg"+";base64,"+anexo.bytes;
        }
        return null;
    };
    
    var iniciarJogo = function (perguntas) {
        $scope.model.perguntas = perguntas;
        $scope.model.pergunta = perguntas[$scope.model.posicao];
        $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
        $scope.model.qntVitoriasNecessaria = Math.floor($scope.model.perguntas.length/3);
        $scope.telaInit = false;
        $rootScope.appLoaded = true;
        $scope.contagemInicial();
    };
    
    var init = function () {
        $scope.telaInit = false;
        $rootScope.appLoaded = false;
        if (idCursoAluno && idEtapa) {
            Jogo.buscarPerguntasDoJogoGerais({'idEtapa': idEtapa}).$promise.then(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        } else {
            Jogo.buscarPerguntasDaApresentacaoDoDaVelhaApresentacao(function (perguntas) {
                iniciarJogo(perguntas);
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        }
    };
    init();
}]);