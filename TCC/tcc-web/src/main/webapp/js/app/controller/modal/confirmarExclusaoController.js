tccApp.controller('ConfirmarExclusaoController', ['$scope', '$modalInstance', 'infor',
    function ($scope, $modalInstance, infor) {
        $scope.titulo = infor.titulo;
        $scope.campos = infor.campos;

        $scope.confirmar = function (excluir) {
            $modalInstance.close(excluir);
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);