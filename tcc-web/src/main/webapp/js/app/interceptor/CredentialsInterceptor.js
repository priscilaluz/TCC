tccApp.factory('CredentialsInterceptor', ['$rootScope', '$location', '$cookies',
    function($rootScope, $location, $cookies) {
        var CredentialsInterceptor = {
            request: function(request) {
                // Verificar se usuario estah autenticado
                $rootScope.autenticar = $cookies.getObject('usuarioLogado');
                if (!$rootScope.autenticar || !$rootScope.autenticar.nome) {
                    console.log("não logado");
                    $location.path("/login");
                }
                return request;
            }
        };
        return CredentialsInterceptor;
    }]);