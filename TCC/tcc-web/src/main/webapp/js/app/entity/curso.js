tccApp.factory('Curso', ['$resource', function ($resource) {

        var Curso = $resource('/tcc/rest/curso/:verbo', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            buscarCursoPorId: {method: 'GET', params: {verbo: 'buscarPorId'}, isArray: false},
            buscarCursoCompletoPorId: {method: 'GET', params: {verbo: 'buscarCompletoPorId'}, isArray: false},
            buscarCursos: {method: 'GET', params: {verbo: 'buscarCurso'}, isArray: true},
            copiarCurso: {method: 'GET', params: {verbo: 'copiarCurso'}, isArray: false},
            buscarCursosAluno: {method: 'GET', params: {verbo: 'buscarCursosAluno'}, isArray: true},
            updateDisponibilidade: {method: 'GET', params: {verbo: 'updateDisponibilidade'}, isArray: false},
            deletarCurso: {method: 'DELETE', params: {verbo: 'deletarCurso'}, isArray: false}
        });

        return Curso;
}]);