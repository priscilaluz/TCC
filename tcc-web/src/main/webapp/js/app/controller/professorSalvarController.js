tccApp.controller('ProfessorSalvarController', ['$scope', '$rootScope', '$routeParams', '$location', 'Usuario', 'growl',
    function ($scope, $rootScope, $routeParams, $location, Usuario, growl) {
        $scope.model = {
            professor:  new Usuario()
        };
        var idProfessor = $routeParams.idProfessor;
        
        $scope.salvarProfessor = function () {
            $rootScope.appLoaded = false;
            $scope.model.professor.$saveProfessor(function () {
                growl.success('Cadastro realizado com sucesso.',{title: 'Sucesso'});
                $scope.voltarTela();
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        $scope.voltarTela = function () {
            $location.path("/consultar-professor");
        };
        
        var init = function () {
            if (idProfessor) {
                $rootScope.appLoaded = false;
                Usuario.buscarProfessorPorId({'idProfessores': idProfessor}).$promise.then(function (professor) {
                    $scope.model.professor = professor;
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };
        init();
    }]);