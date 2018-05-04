tccApp.factory('Usuario', ['$resource', function ($resource) {

        var Usuario = $resource('/tcc/rest/usuario/:verbo/:id', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            saveProfessor: {method: 'POST', params: {verbo: 'saveProfessor'}, isArray: false},
            buscarUsuario: {method: 'GET', params: {verbo: 'buscarUsuario'}, isArray: false},
            dadosProfessor: {method: 'GET', params: {verbo: 'dadosProfessor'}, isArray: false},
            buscarProfessores: {method: 'GET', params: {verbo: 'buscarProfessores'}, isArray: true},
            buscarAlunos: {method: 'GET', params: {verbo: 'buscarAlunos'}, isArray: true},
            buscarProfessorPorId: {method: 'GET', params: {verbo: 'buscarProfessorPorId'}, isArray: false},
            deletarProfessor: {method: 'DELETE', params: {verbo: 'deletarProfessor'}, isArray: false}
        });

        return Usuario;
}]);