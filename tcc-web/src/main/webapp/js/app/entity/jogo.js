tccApp.factory('Jogo', ['$resource', function ($resource) {

        var Jogo = $resource('/tcc/rest/jogoPerguntas/:verbo', {}, {
            buscarPerguntaDaApresentacaoDoJogo: {method: 'GET', params: {verbo: 'quizApresentacao'}, isArray: true}
        });

        return Jogo;
}]);