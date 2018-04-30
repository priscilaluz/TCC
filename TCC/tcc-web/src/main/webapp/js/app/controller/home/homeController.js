tccApp.controller('HomeController', ['$scope', '$rootScope',
    function ($scope, $rootScope) {
        $rootScope.telaHomeAluno = false;
        $rootScope.appLoaded = true;
        $scope.init = function (){
            $scope.titulo = "TELA INICIAL";
            $rootScope.isAdministrador = $rootScope.autenticar.tipo.id === "D";
            $rootScope.isAluno = $rootScope.autenticar.tipo.id === "A";
            $rootScope.isProfessor = $rootScope.autenticar.tipo.id === "P";
        };
        
        $scope.init();
}]);