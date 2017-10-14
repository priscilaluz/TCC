tccApp.factory('CursoAluno', ['$resource', function ($resource) {

        var Curso = $resource('/tcc/rest/curso-aluno/:verbo', {}, {
            buscarCursoAlunoPorAlunoSituacao: {method: 'GET', params: {verbo: 'buscarCursoAlunoPorAlunoSituacao'}, isArray: true},
            entrarCurso: {method: 'GET', params: {verbo: 'entrarCurso'}, isArray: false}
        });

        return Curso;
}]);