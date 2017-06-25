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
      when('/criar-curso', {
        templateUrl: 'partials/curso/curso-salvar.html',
        controller: 'CursoController'
      }).
      when('/sobre', {
        templateUrl: 'partials/home/sobre.html',
        controller: 'SobreController'
      }).
              
      otherwise({
        redirectTo: '/home'
      });
  }]);