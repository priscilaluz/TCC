tccApp.factory('Categoria', ['$resource', function ($resource) {

        var Categoria = $resource('/tcc/rest/categoria/:verbo', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            deletarCategoria: {method: 'DELETE', params: {verbo: 'deletar'}, isArray: false},
            buscarCategoriaPorId: {method: 'GET', params: {verbo: 'buscarPorId'}, isArray: false},
            buscarTodasCategoria: {method: 'GET', params: {verbo: 'buscarTodas'}, isArray: true},
            buscarCategoriaPorFiltro: {method: 'GET', params: {verbo: 'buscarPorFiltro'}, isArray: false}
        });

        return Categoria;
}]);