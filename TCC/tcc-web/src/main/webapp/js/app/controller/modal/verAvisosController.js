tccApp.controller('VerAvisosController', ['$scope', '$modalInstance', 'aviso',
    function ($scope, $modalInstance, aviso) {
        $scope.model = {
            aviso: aviso
        };
        
        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);