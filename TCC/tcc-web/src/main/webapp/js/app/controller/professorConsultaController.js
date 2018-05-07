tccApp.controller('ProfessorConsultaController', ['$scope', '$rootScope', '$modal', '$location', 'Usuario', 'growl',
    function ($scope, $rootScope, $modal, $location, Usuario, growl) {
        $rootScope.telaHomeAluno = false;
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
        
        $scope.excluirProfessor = function (professor) {
            var infor = {titulo:'Deseja realmente excluir professor?', campos: []};
            infor.campos.push({titulo: 'Nome:', descricao: professor.nome});
            infor.campos.push({titulo: 'Login:', descricao: professor.login});
            infor.campos.push({titulo: 'Email:', descricao: professor.email});
            $modal.open({
                templateUrl: 'partials/confirmar-exclusao.html',
                controller: 'ConfirmarExclusaoController',
                size: 'md',
                resolve: {infor: function () {return infor;}}
            }).result.then(function (excluir) {
                if (excluir) {
                    $rootScope.appLoaded = false;
                    Usuario.deletarProfessor({'idProfessores':professor.id}).$promise.then(function (result) {
                        growl.success('Professor excluído com sucesso.',{title: 'Operação bem sucedida'});
                        $scope.pesquisar();
                        $rootScope.appLoaded = true;
                    }, function (error) {
                        $rootScope.appLoaded = true;
                    });
                }
            }, function () {
                // Modal cancelado
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