tccApp.factory('Aviso', ['$resource', function ($resource) {

        var Aviso = $resource('/tcc/rest/curso/aviso/:verbo', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            deletar: {method: 'DELETE', params: {verbo: 'deletar'}, isArray: false},
            buscarById: {method: 'GET', params: {verbo: 'buscarById'}, isArray: false},
            buscarTodos: {method: 'GET', params: {verbo: 'buscarTodos'}, isArray: true}
        });

        return Aviso;
}]);