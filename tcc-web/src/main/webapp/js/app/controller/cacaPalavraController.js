tccApp.controller('CacaPalavraController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    $scope.model = {
        primeiraCedula: null,
        matrizCompleta: null,
        formarPalavra: []
    };
    var corSelecionada = '#5396d4';
    var corQuandoPassarPor = '#9bcffd';
    var corInicial = '#8ab7de';
    
    var addListaDeLetrasSelecionadas = function (i, j) {
        $scope.model.formarPalavra.push({x:i, y:j, letra: angular.copy($scope.model.matrizCompleta[i][j])});
    };
    
    //Se clicar na cell
    // - cell.selecionado = true;
    // - add a lista de cell selecionadas
    $scope.mouseDownCelula = function (i, j) {
        $scope.model.primeiraCedula = null;
        backgroundMatrix();
        $scope.model.primeiraCedula = {x:i, y:j, letra: angular.copy($scope.model.matrizCompleta[i][j])};
        $scope.model.matrizCompleta[i][j].selecionado=true;
        $scope.model.matrizCompleta[i][j].style = {'background-color': corSelecionada};
        addListaDeLetrasSelecionadas(i, j);
    };
    
    //Se “desclicar” na cell
    // - verificar se tds a celulas selecionadas formam uma palavra
    // - se sim, salvar palavra cell da palavra colocar variável com palavra
    // - se não, ignorar
    // - após, cells.selecionado = false;
    $scope.mouseUpCelula = function (i, j) {
        $scope.model.primeiraCedula = null;
        backgroundMatrix();
    };
    
    //Se entrar na cell
    // - sendo que a primeira clicada foi X,Y então
    // - se entro em I,J
    // - se X == I
    //	então remove tds seleções existentes e selecionar tds entre Y,J
    // - se J == Y
    //	então remove tds seleções existentes e selecionar tds entre X,I
    // - se (X-I)==(Y-J)
    //	então remove tds seleções existentes e selecionar tds somando assim:
    //		(x,y);(x+1,y+1);(x+2,y+2)...(i,j) ou
    //		(i,j);(i+1,j+1);(i+2,j+2)...(x,y) a depender de quem é maior
    // - se não
    //	então remove tds seleções existentes
    $scope.mouseOverCelula = function (i, j) {
        if ($scope.model.primeiraCedula) {
            backgroundMatrix();
            if (i === $scope.model.primeiraCedula.x) {
                var yMenor = $scope.model.primeiraCedula.y < j ? $scope.model.primeiraCedula.y : j;
                var yMaior = $scope.model.primeiraCedula.y > j ? $scope.model.primeiraCedula.y : j;
                for (var b = yMenor; b <= yMaior; b++) {
                    $scope.model.matrizCompleta[i][b].style = {'background-color': corSelecionada};
                }
            } else if (j === $scope.model.primeiraCedula.y) {
                var xMenor = $scope.model.primeiraCedula.x < i ? $scope.model.primeiraCedula.x : i;
                var xMaior = $scope.model.primeiraCedula.x > i ? $scope.model.primeiraCedula.x : i;
                for (var a = xMenor; a <= xMaior; a++) {
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
        if ($scope.model.primeiraCedula) {
            
        } else {
            if (!$scope.model.matrizCompleta[i][j].comPalavra) {
                $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
            }
        }
    };
    
    var backgroundMatrix = function () {
        for (var i = 0; i < 10; i++) {
            for (var j = 0; j < 10; j++) {
                if (!$scope.model.matrizCompleta[i][j].comPalavra) {
                    $scope.model.matrizCompleta[i][j].style = {'background-color': corInicial};
                }
            }
        }
    };
    
    var init = function () {
        $rootScope.appLoaded = false;
        Jogo.buscarPerguntasDaApresentacaoDoJogoCacaPalavra(function (matriz) {
            $scope.model.matrizCompleta = matriz;
            backgroundMatrix();
            $rootScope.appLoaded = true;
        }, function (error) {
            $rootScope.appLoaded = true;
        });
    };
    init();
}]);


/*Se clicar na cell
 - cell.selecionado = true;
 - add a lista de cell selecionadas
Se “desclicar” na cell
 - verificar se tds a celulas selecionadas formam uma palavra
 - se sim, salvar palavra cell da palavra colocar variável com palavra
 - se não, ignorar
 - após, cells.selecionado = false;
Se entrar na cell
 - sendo que a primeira clicada foi X,Y então
 - se entro em I,J
 - se X == I
	então remove tds seleções existentes e selecionar tds entre Y,J
 - se J == Y
	então remove tds seleções existentes e selecionar tds entre X,I
 - se (X-I)==(Y-J)
	então remove tds seleções existentes e selecionar tds somando assim:
		(x,y);(x+1,y+1);(x+2,y+2)...(i,j) ou
		(i,j);(i+1,j+1);(i+2,j+2)...(x,y) a depender de quem é maior
 - se não
	então remove tds seleções existentes
*/