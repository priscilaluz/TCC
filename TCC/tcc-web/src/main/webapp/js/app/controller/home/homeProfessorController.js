tccApp.controller('HomeProfessorController', ['$scope', '$rootScope', '$location', 'Curso', 'Usuario',
    function ($scope, $rootScope, $location, Curso, Usuario) {
        $rootScope.telaHomeAluno = false;
        
        $scope.pergunta = function () {
            $location.path("/consultar-pergunta");
        };
        
        $scope.criarCurso = function () {
            $location.path("/criar-curso");
        };
        
        $scope.tabuleiro = function () {
            $location.path("/tabuleiro");
        };
        
        $scope.jogos = function () {
            $location.path("/jogos");
        };
        
        $scope.init = function (){
            $rootScope.appLoaded = false;
            Curso.buscarCursosNome({'idUsuario': $rootScope.usuarioLogado.id}, function (result) {
                $scope.cursos = result;
                $rootScope.appLoaded = true;
                Usuario.dadosProfessor({'idProfessores': $rootScope.usuarioLogado.id}, function (professor) {
                    $scope.professor = professor;
                    $rootScope.appLoaded = true;
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }, function (error) {
                $rootScope.appLoaded = true;
            });
            $scope.alunos = [];
            $scope.alunos.push({'nome':'Joao', 'pontos': 1000});
            $scope.alunos.push({'nome':'Maria', 'pontos': 1300});
        };
        
        $scope.init();
}]);