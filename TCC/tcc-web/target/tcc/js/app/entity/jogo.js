tccApp.factory('Jogo', ['$resource', function ($resource) {

        var Jogo = $resource('/tcc/rest/jogoPerguntas/:verbo', {}, {
            buscarPerguntaDaApresentacaoDoJogoQuiz: {method: 'GET', params: {verbo: 'quizApresentacao'}, isArray: true},
            buscarPerguntaDaApresentacaoDoJogoForca: {method: 'GET', params: {verbo: 'forcaApresentacao'}, isArray: true},
            buscarPerguntaDaApresentacaoDoJogoAposta: {method: 'GET', params: {verbo: 'apostaApresentacao'}, isArray: true},
            buscarPerguntasDaApresentacaoDoJogoCacaPalavra: {method: 'GET', params: {verbo: 'cacaPalavraApresentacao'}, isArray: false},
            buscarPerguntaDosJogosQuizForcaAposta: {method: 'GET', params: {verbo: 'quizForcaAposta'}, isArray: true},
            buscarPerguntasDoJogoCacaPalavra: {method: 'GET', params: {verbo: 'cacaPalavra'}, isArray: false}
        });

        return Jogo;
}]);