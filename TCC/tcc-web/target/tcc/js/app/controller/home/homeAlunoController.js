tccApp.controller('HomeAlunoController', ['$scope', '$rootScope', '$location',
    function ($scope, $rootScope, $location) {
        $rootScope.telaHomeAluno = true;
        $scope.direcionarPara = function (url) {
            $location.path(url);
        };
        
        $scope.init();
}]);