tccApp.factory('CursoAluno', ['$resource', function ($resource) {

        var CursoAluno = $resource('/tcc/rest/curso-aluno/:verbo', {}, {
            buscarCursoAlunoPorAlunoSituacao: {method: 'GET', params: {verbo: 'buscarCursoAlunoPorAlunoSituacao'}, isArray: true},
            entrarCurso: {method: 'GET', params: {verbo: 'entrarCurso'}, isArray: false},
            buscarCursoAlunoPorIdCursoAluno: {method: 'GET', params: {verbo: 'buscarCursoAlunoPorIdCursoAluno'}, isArray: false},
            buscarEtapaAlunoPorCursoAlunoEEtapa: {method: 'GET', params: {verbo: 'buscarEtapaAlunoPorCursoAlunoEEtapa'}, isArray: false},
            salvarEtapaAluno: {method: 'GET', params: {verbo: 'salvarEtapaAluno'}, isArray: false},
            buscarRelatoriosEtapaPorId: {method: 'GET', params: {verbo: 'relatoriosPorId'}, isArray: false},
            buscarRelatoriosEtapaPorIdEtapaAluno: {method: 'GET', params: {verbo: 'relatoriosPorIdEtapaAluno'}, isArray: true},
            buscarCursoAlunoPorIdCurso: {method: 'GET', params: {verbo: 'alunosPorIdCurso'}, isArray: true},
            buscarProprioAndamento: {method: 'GET', params: {verbo: 'proprioAndamento'}, isArray: true}
        });

        return CursoAluno;
}]);