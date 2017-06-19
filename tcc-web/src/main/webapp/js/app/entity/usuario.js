tccApp.factory('Usuario', ['$resource', function ($resource) {

        var Usuario = $resource('/tcc/rest/usuario/:verbo/:id', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            buscarUsuario: {method: 'GET', params: {verbo: 'buscarUsuario'}, isArray: false}
        });

        return Usuario;
}]);