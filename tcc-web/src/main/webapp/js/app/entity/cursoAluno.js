tccApp.factory('CursoAluno', ['$resource', function ($resource) {

        var Curso = $resource('/tcc/rest/curso-aluno/:verbo', {}, {
            buscarCursoAlunoPorAlunoSituacao: {method: 'GET', params: {verbo: 'buscarCursoAlunoPorAlunoSituacao'}, isArray: true},
            entrarCurso: {method: 'GET', params: {verbo: 'entrarCurso'}, isArray: false},
            buscarCursoAlunoPorIdCursoAluno: {method: 'GET', params: {verbo: 'buscarCursoAlunoPorIdCursoAluno'}, isArray: false},
            buscarEtapaAlunoPorCursoAlunoEEtapa: {method: 'GET', params: {verbo: 'buscarEtapaAlunoPorCursoAlunoEEtapa'}, isArray: false}
        });

        return Curso;
}]);