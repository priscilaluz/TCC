tccApp.factory('Enums', ['$resource', function ($resource) {

        var Enums = $resource('/tcc/rest/enums/:verbo/:id', {}, {
            getJogos: {method: 'GET', params: { verbo: 'jogos'}, isArray: true},
            getCategorias: {method: 'GET', params: { verbo: 'categorias'}, isArray: true},
            getSituacoesCurso: {method: 'GET', params: { verbo: 'situacaoCurso'}, isArray: true}
        });

        return Enums;
}]);