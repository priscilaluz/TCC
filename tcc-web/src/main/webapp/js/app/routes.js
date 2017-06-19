tccApp.run(['$rootScope', 'growl', function ($rootScope, growl) {
    $rootScope.$on('snRequestException', function (event, data) { 
        growl.error(data.message,{title: 'Error'});
    });
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
              
      otherwise({
        redirectTo: '/home'
      });
  }]);