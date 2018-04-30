tccApp.controller('HomeAdmController', ['$scope', '$rootScope', '$location',
    function ($scope, $rootScope, $location) {
        $rootScope.telaHomeAluno = false;
        
        $scope.mouseoverProfessor = function () {
            $scope.srcProfessor = "img/professor-on.png";
        };
        $scope.mouseleaveProfessor = function () {
            $scope.srcProfessor = "img/professor.png";
        };
        $scope.professor = function () {
            $location.path("/consultar-professor");
        };
        
        $scope.mouseoverCategoria = function () {
            $scope.srcCategoria = "img/categoria-on.png";
        };
        $scope.mouseleaveCategoria = function () {
            $scope.srcCategoria = "img/categoria.png";
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