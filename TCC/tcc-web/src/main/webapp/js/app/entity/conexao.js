tccApp.factory('Conexao', ['$resource', function ($resource) {

        var Conexao = $resource('/tcc/rest/conexao/:verbo', {}, {
            buscar: {method: 'GET', params: {verbo: 'buscar'}, isArray: false}
        });

        return Conexao;
}]);