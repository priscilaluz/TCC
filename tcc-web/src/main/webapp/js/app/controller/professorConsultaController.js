tccApp.controller('ProfessorConsultaController', ['$scope', '$rootScope', '$location', 'Usuario', 'growl',
    function ($scope, $rootScope, $location, Usuario, growl) {
        $scope.model = {
            nomeProfessor: null,
            professores: []
        };
        
        $scope.pesquisar = function () {
            $rootScope.appLoaded = false;
            Usuario.buscarProfessores({'nome': $scope.model.nomeProfessor},function (result) {
                $scope.model.professores = result;
                $rootScope.appLoaded = true;
            },function(error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.excluirProfessor = function (id) {
            $rootScope.appLoaded = false;
            Usuario.deletarProfessor({'idProfessores':id}).$promise.then(function (result) {
                growl.success('Professor excluído com sucesso.',{title: 'Operação bem sucedida'});
                $scope.pesquisar();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.telaNovaEditar = function (idProfessor) {
            var comId = idProfessor?"/"+idProfessor:"";
            $location.path("/salvar-professor"+comId);
        };
        
        var init = function () {
            $rootScope.appLoaded = false;
            $rootScope.appLoaded = true;
        };
        init();
    }]);