tccApp.factory('Premio', ['$resource', function ($resource) {

        var Premio = $resource('/tcc/rest/premio/:verbo', {}, {
            buscarPremioPorIdAluno: {method: 'GET', params: {verbo: 'buscarPorIdAluno'}, isArray: false}
        });

        return Premio;
}]);