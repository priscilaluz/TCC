tccApp.factory('Enums', ['$resource', function ($resource) {

        var Enums = $resource('/tcc/rest/enums/:verbo/:id', {}, {
            getJogos: {method: 'GET', params: { verbo: 'jogos'}, isArray: true},
            getSituacoesCurso: {method: 'GET', params: { verbo: 'situacaoCurso'}, isArray: true},
            getTiposPerguntaPorJogo: {method: 'GET', params: { verbo: 'tipoPerguntaPorJogo'}, isArray: false},
            getTiposPergunta: {method: 'GET', params: { verbo: 'tipoPergunta'}, isArray: true},
            getNiveisPergunta: {method: 'GET', params: { verbo: 'nivelPergunta'}, isArray: true}
        });

        return Enums;
}]);