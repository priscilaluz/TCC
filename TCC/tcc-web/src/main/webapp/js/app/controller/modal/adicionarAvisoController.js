tccApp.controller('AdicionarAvisoController', ['$scope', '$rootScope', '$modalInstance', 'obj', 'Aviso',
    function ($scope, $rootScope, $modalInstance, obj, Aviso) {
        $scope.model = {
            aviso: new Aviso()
        };

        $scope.salvarAviso = function () {
            $scope.model.aviso.$save(function () {
                $modalInstance.close();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        var init = function () {
            if (obj.aviso) {
                $scope.model.aviso.id = obj.aviso.id;
                $scope.model.aviso.titulo = obj.aviso.titulo;
                $scope.model.aviso.descricao = obj.aviso.descricao;
            }
            $scope.model.aviso.idCurso = obj.idCurso;
        };
        init();
    }]);