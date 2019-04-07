tccApp.factory('CredentialsInterceptor', ['$rootScope', '$location', '$cookies',
    function($rootScope, $location, $cookies) {
        var CredentialsInterceptor = {
            request: function(request) {
                // Verificar se usuario estah autenticado
                $rootScope.autenticar = $cookies.getObject('usuarioLogado');
                var telaSobre = $location.$$path==="/sobre";
                var telaLogin = $location.$$path==="/login";
                if (!telaSobre && !telaLogin && (!$rootScope.autenticar || !$rootScope.autenticar.nome)) {
                    console.log("não logado");
                    $location.path("/login");
                }
                return request;
            }
        };
        return CredentialsInterceptor;
    }]);