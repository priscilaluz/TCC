tccApp.controller('BuscarAvisosController', ['$scope', '$rootScope', '$modalInstance', '$modal', 'obj', 'Aviso',
    function ($scope, $rootScope, $modalInstance, $modal, obj, Aviso) {
        $scope.model = {
            avisos: []
        };
        
        $scope.fechar = function () {
            $modalInstance.dismiss('cancel');
        };
        
        $scope.visualizar = function (aviso) {
            $modal.open({
                templateUrl: 'partials/curso/consultar-todos-aluno/ver-aviso.html',
                controller: 'VerAvisosController',
                size: 'md',
                resolve: {aviso: function () {return aviso;}}
            }).result.then(function (result) {
                buscarAvisos();
            }, function () {
                // Modal cancelado
            });
        };
        
        var init = function () {
            var idCurso = obj.idCurso;
            $rootScope.appLoaded = false;
            Aviso.buscarTodos({'idCurso': idCurso}, function (result) {
                $scope.model.avisos = result;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        init();
    }]);