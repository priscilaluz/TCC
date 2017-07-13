tccApp.factory('Enums', ['$resource', function ($resource) {

        var Enums = $resource('/tcc/rest/enums/:verbo/:id', {}, {
            getJogos: {method: 'GET', params: { verbo: 'jogos'}, isArray: true},
            getCategorias: {method: 'GET', params: { verbo: 'categorias'}, isArray: true},
            getSituacoesCurso: {method: 'GET', params: { verbo: 'situacaoCurso'}, isArray: true},
            getTiposPergunta: {method: 'GET', params: { verbo: 'tipoPergunta'}, isArray: true},
            getNiveisPergunta: {method: 'GET', params: { verbo: 'nivelPergunta'}, isArray: true}
        });

        return Enums;
}]);