tccApp.controller('QuizController', ['$scope', '$rootScope', '$location', '$timeout',
function ($scope, $rootScope, $location, $timeout) {
    $rootScope.contagem = true;
    $scope.count = 0;
    $scope.voltar = function () {
        $location.path("/jogos");
    };
    
    $scope.increment = function() {
      $scope.count++;
      if ($scope.count < 5) {
        $timeout($scope.increment, 1000);
      } else {
          $rootScope.contagem = false;
      }
    };
    $scope.increment();
    
    $scope.init = function () {

    };
}]);