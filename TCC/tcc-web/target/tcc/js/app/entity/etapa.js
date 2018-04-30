tccApp.factory('Etapa', ['$resource', function ($resource) {

        var Etapa = $resource('/tcc/rest/curso/etapa/:verbo', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            deletar: {method: 'DELETE', params: {verbo: 'deletar'}, isArray: false},
            buscar: {method: 'GET', params: {verbo: 'buscar'}, isArray: false}
        });

        return Etapa;
}]);