tccApp.controller('DicaController', ['$scope', '$modalInstance', 'dica',
    function ($scope, $modalInstance, dica) {
        $scope.model = {
            dica: dica
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
        };
        init();
    }]);