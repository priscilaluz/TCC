tccApp.controller('HomeController', ['$scope', '$rootScope',
    function ($scope, $rootScope) {
        $rootScope.appLoaded = true;
        $scope.init = function (){
            $scope.titulo = "TELA INICIAL";
        };
        
        $scope.init();
}]);