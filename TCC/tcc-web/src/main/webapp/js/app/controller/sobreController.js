tccApp.controller('SobreController', ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {
    $rootScope.telaSobre = true;
    $rootScope.telaHomeAluno = false;
    
    $rootScope.telaLogin = function () {
        $rootScope.telaSobre = false;
        $location.path("/login");
    };
}]);