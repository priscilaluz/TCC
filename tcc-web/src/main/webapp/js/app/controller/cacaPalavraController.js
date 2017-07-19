tccApp.controller('CacaPalavraController', ['$scope', '$rootScope', '$modal', '$location', '$timeout', 'Jogo',
function ($scope, $rootScope, $modal, $location, $timeout, Jogo) {
    $scope.model = {
        primeiraCedula: null,
        matrizCompleta: null
    };
    $scope.alfabeto = ["A","B","C","D","E","F","G","H","I","J","K","L",
        "M","N","O","P","Q","R","S","U","V","W","X","Y","Z"];
    
    $scope.mouseOverCelula = function (i, j) {
        if ($scope.model.primeiraCedula){
            
        }else{
            $scope.model.matrizCompleta[i][j].style = {'background-color': '#9bcffd'};
        }
    };
    
    $scope.mouseLeaveCelula = function (i, j) {
        if ($scope.model.primeiraCedula){
            
        }else{
            if (!$scope.model.matrizCompleta[i][j].comPalavra){
                $scope.model.matrizCompleta[i][j].style = {'background-color': '#8ab7de'};
            }
        }
    };
    
    var backgroundMatrix = function () {
        for (var i = 0; i < 10; i++) {
            for (var j = 0; j < 10; j++) {
                if (!$scope.model.matrizCompleta[i][j].comPalavra){
                    $scope.model.matrizCompleta[i][j].style = {
                        'background-color': '#8ab7de'
                    };
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
 - sendo a primeira clicada foi X,Y então
 - se entro em I,J
 - se X == I
	então remove tds seleções existentes e seleciona tds entre Y,J
 - se J == Y
	então remove tds seleções existentes e seleciona tds entre X,I
 - se (X-I)==(Y-J)
	então remove tds seleções existentes e seleciona tds somando assim:
		(x,y);(x+1,y+1);(x+2,y+2)...(i,j) ou
		(i,j);(i+1,j+1);(i+2,j+2)...(x,y) a depender de quem é maior
 - se não
	então remove tds seleções existentes
*/