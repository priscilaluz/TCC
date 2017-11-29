tccApp.controller('LoginController', ['$scope', 'Usuario', 'growl', '$cookies', '$rootScope', '$location',
    function ($scope, Usuario, growl, $cookies, $rootScope, $location) {
        $cookies.remove('usuarioLogado');
        $scope.usuario = new Usuario();
        $rootScope.usuarioLogado = null;
        $rootScope.appLoaded = true;
        $scope.telaCadastro = false;
        $scope.telaLogin = false;
        
        $rootScope.logarTela = function () {
            $scope.usuario = new Usuario();
            $scope.telaCadastro = false;
            $scope.telaLogin = true;
        };
        
        $rootScope.cadastrarTela = function () {
            $scope.usuario = new Usuario();
            $scope.telaCadastro = true;
            $scope.telaLogin = false;
        };
        
        $scope.voltarTela = function () {
            $scope.usuario = new Usuario();
            $scope.telaLogin = false;
            $scope.telaCadastro = false;
        };
        
        $scope.cadastrar = function () {
            $rootScope.appLoaded = false;
            $scope.usuario.$save(function () {
                growl.success('Cadastro realizado com sucesso.',{title: 'Sucesso'});
                $scope.voltarTela();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };

        $scope.logar = function () {
            $rootScope.appLoaded = false;
            Usuario.buscarUsuario({'login': $scope.usuario.login,'senha': $scope.usuario.senha},function (result) {
                if (result && result.nome){
                    $rootScope.usuarioLogado = result;
                    $rootScope.autenticar = $cookies.getObject('usuarioLogado');
                    $rootScope.isAdministrador = result.tipo.id === "D";
                    $rootScope.isAluno = result.tipo.id === "A";
                    $rootScope.isProfessor = result.tipo.id === "P";
                    $cookies.putObject('usuarioLogado', $rootScope.usuarioLogado);
                    $location.path("/home");
                }else{
                    growl.error("Senha ou usuário inválido.",{title: 'Error'});
                }
                $rootScope.appLoaded = true;
            },function(error) {
                $rootScope.appLoaded = true;
            });
        };
}]);