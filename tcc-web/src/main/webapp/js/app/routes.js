tccApp.run(['$rootScope', 'growl', '$cookies', '$location', function ($rootScope, growl, $cookies, $location) {
    $rootScope.appLoaded = true;
    $rootScope.$on('snRequestException', function (event, data) { 
        growl.error(data.message,{title: 'Error'});
    });
    
    $rootScope.usuarioLogado = $cookies.getObject('usuarioLogado');
    
    $rootScope.logoff = function() {
        $rootScope.usuarioLogado = null;
        $cookies.remove('usuarioLogado');
        $location.path("/login");
    };
}]);

tccApp.config(['$routeProvider', '$httpProvider',
  function($routeProvider, $httpProvider) {
    $httpProvider.interceptors.push('CredentialsInterceptor');
    $httpProvider.interceptors.push('snRequestExceptionInterceptor');
    $routeProvider.
      when('/login', {
        templateUrl: 'partials/login/login.html',
        controller: 'LoginController'
      }).
      when('/home', {
        templateUrl: 'partials/home/home.html',
        controller: 'HomeController'
      }).
      when('/consultar-pergunta', {
        templateUrl: 'partials/pergunta/consulta/pergunta-consulta.html',
        controller: 'PerguntaConsultaController'
      }).
      when('/salvar-pergunta/:idPergunta?', {
        templateUrl: 'partials/pergunta/salvar/pergunta-salvar.html',
        controller: 'PerguntaSalvarController'
      }).
      when('/consultar-curso-criei', {
        templateUrl: 'partials/curso/consultar-meus/curso-consulta.html',
        controller: 'CursoConsultarCrieiController'
      }).
      when('/criar-curso/:idCurso?/:situacao?', {
        templateUrl: 'partials/curso/salvar/curso.html',
        controller: 'CursoSalvarController'
      }).
      when('/sobre', {
        templateUrl: 'partials/home/sobre.html',
        controller: 'SobreController'
      }).
      when('/jogos', {
        templateUrl: 'partials/jogo/jogos.html',
        controller: 'JogoController'
      }).
      when('/jogos-simulado/quiz', {
        templateUrl: 'partials/jogo/quiz/quiz.html',
        controller: 'QuizController'
      }).
      when('/jogos-simulado/forca', {
        templateUrl: 'partials/jogo/forca/forca.html',
        controller: 'ForcaController'
      }).
      when('/jogos-simulado/caca-palavra', {
        templateUrl: 'partials/jogo/caca-palavra/caca-palavra.html',
        controller: 'CacaPalavraController'
      }).
      when('/jogos-simulado/aposta', {
        templateUrl: 'partials/jogo/aposta/aposta.html',
        controller: 'ApostaController'
      }).
      when('/tabuleiro', {
        templateUrl: 'partials/tabuleiro/tabuleiro.html',
        controller: 'TabuleiroController'
      }).

      otherwise({
        redirectTo: '/home'
      });
  }]);