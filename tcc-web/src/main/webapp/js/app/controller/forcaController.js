tccApp.controller('ForcaController', ['$scope', '$rootScope', '$routeParams', '$modal', '$location', '$timeout', 'Jogo', 'RelatorioEtapa',
    function ($scope, $rootScope, $routeParams, $modal, $location, $timeout, Jogo, RelatorioEtapa) {
        $rootScope.contagem = true;
        var imgCampoVazio = "img/jogos/forca/Letras/CampoVazio.png";
        var espaco = "img/jogos/forca/Letras/espaco.png";
        var pontuacaoMinima = 0;
        var idCursoAluno = $routeParams.idCursoAluno;
        var idEtapa = $routeParams.idEtapa;
        var idEtapaAluno = $routeParams.idEtapaAluno;
        $scope.count = 0;
        $scope.imagem1 = true;
        $scope.model = {
            jogo:"Forca",
            pontuacao: 0,
            posicao: 0,
            pergunta: null,
            letrasEscolhidas: [],
            perguntas: [],
            resultados: [],
            qntPulo: 1,
            qntDica: 1,
            perdeuJogo: false,
            dica: false,
            resultado: false
        };
        var erro = 1;
        $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca" + erro + ".png";

        $scope.voltar = function () {
            if (idCursoAluno && idEtapa){
                $location.path("/cursar-etapa/"+idCursoAluno+"/"+idEtapa+"/"+idEtapaAluno);
            } else {
                $location.path("/jogos");
            }
        };

        $scope.dicaPergunta = function () {
            var dica = "Essa pergunta não tem dica.";
            if ($scope.model.pergunta.dica) {
                dica = $scope.model.pergunta.dica;
                $scope.model.dica = true;
                $scope.model.qntDica--;
            }
            var obj = {'dica': dica};
            $modal.open({
                templateUrl: 'partials/jogo/dica.html',
                controller: 'DicaController',
                resolve: {obj: function () {return obj;}}
            }).result.then(function (result) {}, function () {});
        };

        $scope.pularPergunta = function () {
            $scope.model.pulo = true;
            $scope.model.qntPulo--;
            addAoRelatorioFinal(null);
        };

        var addAoRelatorioFinal = function (ganhou) {
            var respostaCorreta = $scope.model.pergunta.respostas[0];
            var resposta = ganhou?respostaCorreta:null;
            for (var i = 0; i < $scope.model.letras.length; i++) {
                if (!$scope.model.letras[i].escolhida) {
                    var letra = buscarLetrasComAcentos($scope.model.letras[i].letra)[0];
                    var letraCerta = buscarImgLetrasComAcentos($scope.model.letras[i].letra, letra);
                    $scope.model.letras[i].imagem = "img/jogos/forca/Letras/" + letraCerta + "-incorreto.png";
                }
            }
            
            $scope.model.resultados.push({'respostaEscolhida': resposta, 'respostaCorreta': respostaCorreta, 'ganhou':ganhou,
                'pergunta': $scope.model.pergunta, 'pulo':  $scope.model.pulo, 'dica':  $scope.model.dica, 'pontuacao': $scope.model.pontuacao});
            
            $scope.model.pulo = false;
            $scope.model.dica = false;
            $timeout(proximaPergunta, 600);
        };
        
        var proximaPergunta = function (){
            $scope.model.posicao++;
            barraDeProgresso();
            if (($scope.model.posicao+1) <= $scope.model.perguntas.length) {
                $scope.model.pergunta = $scope.model.perguntas[$scope.model.posicao];
                $scope.model.anexoString = exibirAnexo($scope.model.pergunta.anexo);
                $scope.model.letrasEscolhidas = [];
                inicializarLetras();
            } else {
                $scope.model.anexoString = null;
                $scope.model.pergunta = null;
                var relatorioEtapa = new RelatorioEtapa();
                relatorioEtapa.etapaAluno = {'id': idEtapaAluno};
                relatorioEtapa.pontuacao = $scope.model.pontuacao;
                relatorioEtapa.perguntasEtapasAlunos = $scope.model.resultados;
                relatorioEtapa.idCursoAluno = idCursoAluno;
                relatorioEtapa.ganhou = ($scope.model.pontuacao >= pontuacaoMinima);
                $rootScope.appLoaded = false;
                relatorioEtapa.$save(function () {
                    if ($scope.model.pontuacao < pontuacaoMinima) {
                        $scope.model.perdeuJogo = true;
                        tempoImagemFimDeJogo();
                    } else {
                        $scope.model.resultado = true;
                    }
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };
        
        var tempoImagemFimDeJogo = function () {
            $scope.imagem1 = !$scope.imagem1;
            $timeout(tempoImagemFimDeJogo, 500);
        };

        var buscarLetrasComAcentos = function (letra) {
            var letrasA = ["A", "Á", "À", "Â", "Ã"];
            var letrasE = ["E", "É", "È", "Ê"];
            var letrasI = ["I", "Í", "Ì", "Î"];
            var letrasO = ["O", "Ó", "Ò", "Õ", "Ô"];
            var letrasU = ["U", "Ú", "Ù", "Û"];
            if (letrasA.indexOf(letra) >= 0) {
                return letrasA;
            }
            if (letrasE.indexOf(letra) >= 0) {
                return letrasE;
            }
            if (letrasI.indexOf(letra) >= 0) {
                return letrasI;
            }
            if (letrasO.indexOf(letra) >= 0) {
                return letrasO;
            }
            if (letrasU.indexOf(letra) >= 0) {
                return letrasU;
            }
            return [letra];
        };

        var buscarImgLetrasComAcentos = function (letraAcento, letraSemAcento) {
            var agudos = ["Á", "É", "Í, Ó", 'Ú'];
            var tils = ["Ã", "Õ"];
            var circunflexos = ["Â", "Ê", "Î", "Ô", "Û"];
            var graves = ["À", "È", "Ì", "Ò", "Ù"];

            if (agudos.indexOf(letraAcento) >= 0) {
                return letraSemAcento + "agudo";
            }
            if (tils.indexOf(letraAcento) >= 0) {
                return letraSemAcento + "til";
            }
            if (circunflexos.indexOf(letraAcento) >= 0) {
                return letraSemAcento + "circunflexo";
            }
            if (graves.indexOf(letraAcento) >= 0) {
                return letraSemAcento + "grave";
            }
            return letraAcento;
        };

        $scope.escolherLetra = function (letra) {
            var letrasAceitadas = buscarLetrasComAcentos(letra);
            $scope.model.letrasEscolhidas.push(letra);
            var contemLetra = false;
            for (var i = 0; i < $scope.model.letras.length; i++) {
                if (letrasAceitadas.indexOf($scope.model.letras[i].letra) >= 0) {
                    var letraCerta = buscarImgLetrasComAcentos($scope.model.letras[i].letra, letra);
                    $scope.model.letras[i].imagem = "img/jogos/forca/Letras/" + letraCerta + "-correto.png";
                    $scope.model.letras[i].escolhida = true;
                    contemLetra = true;
                }
            }
            var todasLetra = true;
            for (var i = 0; i < $scope.model.letras.length; i++) {
                if (!$scope.model.letras[i].escolhida) {
                    todasLetra = false;
                }
            }
            if (todasLetra) {
                $scope.model.pontuacao = $scope.model.pontuacao + 100;
                addAoRelatorioFinal(true);
            }
            if (!contemLetra) {
                erro++;
                if (erro < 7) {
                    $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca" + erro + ".png";
                } else {
                    $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca" + erro + ".png";
                    addAoRelatorioFinal(false);
                }
            }
        };

        var contagemInicial = function () {
            $scope.count++;
            if ($scope.count < 5) {
                $timeout(contagemInicial, 1000);
            } else {
                $rootScope.contagem = false;
            }
        };

        var barraDeProgresso = function () {
            var porcentagem = ($scope.model.posicao + 1) / $scope.model.perguntas.length * 100;
            $scope.barraProgresso = {
                width: porcentagem + '%'
            };
        };

        var inicializarLetras = function () {
            $scope.model.letras = $scope.model.pergunta.respostas[0].letras;
            for (var i = 0; i < $scope.model.letras.length; i++) {
                if ($scope.model.letras[i].letra === " ") {
                    $scope.model.letras[i].imagem = espaco;
                    $scope.model.letras[i].escolhida = true;
                } else {
                    $scope.model.letras[i].imagem = imgCampoVazio;
                    $scope.model.letras[i].escolhida = false;
                }
            }
            erro = 1;
            $scope.model.forcaImg = "img/jogos/forca/Bonecos/forca" + erro + ".png";
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
            var pontuacaoMaxima = perguntas.length*100;
            pontuacaoMinima = pontuacaoMaxima*7/10;
            inicializarLetras();
            $rootScope.appLoaded = true;
            $scope.telaInit = false;
            barraDeProgresso();
            contagemInicial();
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            $scope.telaInit = true;
            if (idCursoAluno && idEtapa) {
                Jogo.buscarPerguntaDosJogosQuizForcaAposta({'idEtapa': idEtapa}).$promise.then(function (perguntas) {
                    iniciarJogo(perguntas);
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            } else {
                Jogo.buscarPerguntaDaApresentacaoDoJogoForca(function (perguntas) {
                    iniciarJogo(perguntas);
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };
        init();
    }]);