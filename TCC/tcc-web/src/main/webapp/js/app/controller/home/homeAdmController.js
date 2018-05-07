tccApp.controller('HomeAdmController', ['$scope', '$rootScope', '$location',
    function ($scope, $rootScope, $location) {
        $rootScope.telaHomeAluno = false;
        $scope.professor = function () {
            $location.path("/consultar-professor");
        };
        
        $scope.categoria = function () {
            $location.path("/consultar-categoria");
        };
        
        $scope.init = function (){
            $rootScope.appLoaded = true;
            $scope.srcProfessor = "img/professor.png";
            $scope.srcCategoria = "img/categoria.png";
        };
        
        $scope.init();
}]);