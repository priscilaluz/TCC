tccApp.controller('HomeController', ['$scope', '$rootScope', '$cookies', 'growl', '$location', 
    function ($scope, $rootScope, $cookies, growl, $location) {
        $rootScope.appLoaded = true;
        $rootScope.usuarioLogado = $cookies.getObject('usuarioLogado');
        $scope.init = function (){
            $scope.titulo = "TELA INICIAL";
        };
        
        $rootScope.logoff = function() {
            $rootScope.usuarioLogado = null;
            $cookies.remove('usuarioLogado');
            $location.path("/login");
        };
        $scope.init();
        
}]);