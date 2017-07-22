tccApp.controller('DicaController', ['$scope', '$modalInstance', 'obj',
    function ($scope, $modalInstance, obj) {
        $scope.model = {
            pergunta: obj.pergunta,
            dica: obj.dica
        };

        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
        };
        init();
    }]);