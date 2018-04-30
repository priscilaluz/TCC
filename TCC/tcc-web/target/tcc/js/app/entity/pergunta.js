tccApp.factory('Pergunta', ['$resource', function ($resource) {

        var Pergunta = $resource('/tcc/rest/pergunta/:verbo', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false},
            buscarPerguntaPorId: {method: 'GET', params: {verbo: 'buscarPorId'}, isArray: false},
            buscarPerguntas: {method: 'GET', params: {verbo: 'buscarPergunta'}, isArray: true},
            deletarPergunta: {method: 'DELETE', params: {verbo: 'deletarPergunta'}, isArray: false}
        });

        return Pergunta;
}]);