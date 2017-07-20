tccApp.controller('CacaPalavraController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    $scope.model = {
        primeiraCedula: null,
        matrizCompleta: null,
        perguntas: [],
        formarPalavra: []
    };
    var tamanhoMatriz = 10;
    var corSelecionada = '#5396d4';
    var corQuandoPassarPor = '#9bcffd';
    var corInicial = '#8ab7de';
    
//$scope.model.formarPalavra.push({x:i, y:j, letra: angular.copy($scope.model.matrizCompleta[i][j])});
    
    $scope.mouseDownCelula = function (i, j) {
        $scope.model.primeiraCedula = null;
        backgroundMatrix();
        $scope.model.primeiraCedula = {x:i, y:j, letra: angular.copy($scope.model.matrizCompleta[i][j])};
        $scope.model.matrizCompleta[i][j].selecionado=true;
        $scope.model.matrizCompleta[i][j].style = {'background-color': corSelecionada};
    };
    
    $scope.mouseUpCelula = function (i, j) {
        if (j >= 0 && i >= 0 && $scope.model.primeiraCedula){
            var palavra = "";
            if (i === $scope.model.primeiraCedula.x) {
                var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;
                var yMaior = $scope.model.primeiraCedula.y > j ? $scope.model.primeiraCedula.y : j;
                for (var b = yMenor; b <= yMaior; b++) {
                    palavra = palavra + $scope.model.matrizCompleta[i][b].letra;
                }
            } else if (j === $scope.model.primeiraCedula.y) {
                var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                var xMaior = $scope.model.primeiraCedula.x > i ? $scope.model.primeiraCedula.x : i;
                for (var a = xMenor; a <= xMaior; a++) {
                    palavra = palavra + $scope.model.matrizCompleta[a][j].letra;
                }
            } else {
                var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                if (($scope.model.primeiraCedula.x-i) === ($scope.model.primeiraCedula.y-j)) {
                    var diagonal = ($scope.model.primeiraCedula.x-i);
                    diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                    var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                    var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;

                    for (var a = 0; a <= diagonal; a++) {
                        palavra = palavra + $scope.model.matrizCompleta[xMenor+a][yMenor+a].letra;
                    }
                } else {
                    var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                    var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                    if (xMenosI === yMenosJ) {
                        var diagonal = ($scope.model.primeiraCedula.x-i);
                        diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                        var xInicio = i;
                        var yInicio = j;

                        if ($scope.model.primeiraCedula.x < i) {
                            xInicio = $scope.model.primeiraCedula.x;
                            yInicio = $scope.model.primeiraCedula.y;
                        }

                        for (var a = 0; a <= diagonal; a++) {
                            palavra = palavra + $scope.model.matrizCompleta[xInicio+a][yInicio-a].letra;
                        }

                    }
                }
            }
            $scope.palavra = palavra;

            $scope.model.primeiraCedula = null;
            backgroundMatrix();
        }
    };
    
    $scope.mouseOverCelula = function (i, j) {
        if ($scope.model.primeiraCedula) {
            backgroundMatrix();
            if (i === $scope.model.primeiraCedula.x) {
                var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;
                var yMaior = $scope.model.primeiraCedula.y > j ? $scope.model.primeiraCedula.y : j;
                for (var b = yMenor; b <= yMaior; b++) {
                    $scope.model.matrizCompleta[i][b].selecionado = true;
                    $scope.model.matrizCompleta[i][b].style = {'background-color': corSelecionada};
                }
            } else if (j === $scope.model.primeiraCedula.y) {
                var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                var xMaior = $scope.model.primeiraCedula.x > i ? $scope.model.primeiraCedula.x : i;
                for (var a = xMenor; a <= xMaior; a++) {
                    $scope.model.matrizCompleta[a][j].selecionado = true;
                    $scope.model.matrizCompleta[a][j].style = {'background-color': corSelecionada};
                }
            } else {
                var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                if (($scope.model.primeiraCedula.x-i) === ($scope.model.primeiraCedula.y-j)) {
                    var diagonal = ($scope.model.primeiraCedula.x-i);
                    diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                    var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                    var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;

                    for (var a = 0; a <= diagonal; a++) {
                        $scope.model.matrizCompleta[xMenor+a][yMenor+a].selecionado = true;
                        $scope.model.matrizCompleta[xMenor+a][yMenor+a].style = {'background-color': corSelecionada};
                    }
                } else {
                    var xMenosI = ($scope.model.primeiraCedula.x-i)>=0?($scope.model.primeiraCedula.x-i):($scope.model.primeiraCedula.x-i)*(-1);
                    var yMenosJ = ($scope.model.primeiraCedula.y-j)>=0?($scope.model.primeiraCedula.y-j):($scope.model.primeiraCedula.y-j)*(-1);
                    if (xMenosI === yMenosJ) {
                        var diagonal = ($scope.model.primeiraCedula.x-i);
                        diagonal = (diagonal>=0)?diagonal:diagonal*(-1);

                        var xInicio = i;
                        var yInicio = j;
                        
                        if ($scope.model.primeiraCedula.x < i) {
                            xInicio = $scope.model.primeiraCedula.x;
                            yInicio = $scope.model.primeiraCedula.y;
                        }
                        
                        for (var a = 0; a <= diagonal; a++) {
                            $scope.model.matrizCompleta[xInicio+a][yInicio-a].selecionado = true;
                            $scope.model.matrizCompleta[xInicio+a][yInicio-a].style = {'background-color': corSelecionada};
                        }

                    }
                }
            }
        } else {
            $scope.model.matrizCompleta[i][j].style = {'background-color': corQuandoPassarPor};
        }
    };
    
    $scope.mouseLeaveCelula = function (i, j) {
        if (!$scope.model.primeiraCedula && !$scope.model.matrizCompleta[i][j].comPalavra) {
            $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
        }
    };
    
    var backgroundMatrix = function () {
        for (var i = 0; i < tamanhoMatriz; i++) {
            for (var j = 0; j < tamanhoMatriz; j++) {
                $scope.model.matrizCompleta[i][j].selecionado = false;
                if (!$scope.model.matrizCompleta[i][j].comPalavra) {
                    $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
                }
            }
        }
    };
    
    var init = function () {
        $rootScope.appLoaded = false;
        Jogo.buscarPerguntasDaApresentacaoDoJogoCacaPalavra(function (cacaPalavra) {
            $scope.model.matrizCompleta = cacaPalavra.matriz;
            $scope.model.perguntas = cacaPalavra.perguntas;
            tamanhoMatriz = cacaPalavra.tamanhoMatriz;
            backgroundMatrix();
            $rootScope.appLoaded = true;
        }, function (error) {
            $rootScope.appLoaded = true;
        });
    };
    init();
}]);