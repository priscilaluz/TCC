tccApp.run(['$rootScope', 'growl', '$cookies', '$location', function ($rootScope, growl, $cookies, $location) {
    $rootScope.appLoaded = true;
    $rootScope.$on('snRequestException', function (event, data) { 
        growl.error(data.message,{title: 'Error'});
    });
    
    $rootScope.usuarioLogado = $cookies.getObject('usuarioLogado');
    if ($rootScope.usuarioLogado) {
        $rootScope.isAdministrador = $rootScope.usuarioLogado.tipo.id === "D";
        $rootScope.isAluno = $rootScope.usuarioLogado.tipo.id === "A";
        $rootScope.isProfessor = $rootScope.usuarioLogado.tipo.id === "P";
    }
    
    $rootScope.logoff = function() {
        $rootScope.usuarioLogado = null;
        $rootScope.isAdministrador = false;
        $rootScope.isAluno = false;
        $rootScope.isProfessor = false;
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
        templateUrl: 'partials/curso/consultar-meus-professor/curso-consulta.html',
        controller: 'CursoConsultarCrieiController'
      }).
      when('/criar-curso/:idCurso?/:situacao?', {
        templateUrl: 'partials/curso/salvar/curso.html',
        controller: 'CursoSalvarController'
      }).
      when('/consultar-todos-curso', {
        templateUrl: 'partials/curso/consultar-todos-aluno/curso-consulta.html',
        controller: 'CursoTodosConsultarController'
      }).
      when('/meus-cursos-aluno', {
        templateUrl: 'partials/curso/meus-cursos-aluno/cursos.html',
        controller: 'MeusCursoAlunoController'
      }).
      when('/sobre', {
        templateUrl: 'partials/home/sobre.html',
        controller: 'SobreController'
      }).
      when('/jogos', {
        templateUrl: 'partials/jogo/jogos.html',
        controller: 'JogoController'
      }).
      when('/jogos-simulado/jogovelha', {
        templateUrl: 'partials/jogo/jogovelha/jogovelha.html',
        controller: 'JogoVelhaController'
      }).
      when('/jogos-simulado/quiz/:idPergunta?', {
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
      when('/jogos-simulado/q/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/jogo/quiz/quiz.html',
        controller: 'QuizController'
      }).
      when('/jogos-simulado/f/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/jogo/forca/forca.html',
        controller: 'ForcaController'
      }).
      when('/jogos-simulado/c/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/jogo/caca-palavra/caca-palavra.html',
        controller: 'CacaPalavraController'
      }).
      when('/jogos-simulado/a/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/jogo/aposta/aposta.html',
        controller: 'ApostaController'
      }).
      when('/jogos-simulado/v/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/jogo/jogovelha/jogovelha.html',
        controller: 'JogoVelhaController'
      }).
      when('/tabuleiro', {
        templateUrl: 'partials/tabuleiro/tabuleiro.html',
        controller: 'TabuleiroController'
      }).
      when('/aluno-cursando/:id', {
        templateUrl: 'partials/aluno-cursando/tabuleiro.html',
        controller: 'AlunoCursandoController'
      }).
      when('/cursar-etapa/:idCursoAluno/:idEtapa/:idEtapaAluno/:aberto', {
        templateUrl: 'partials/cursar-etapa/etapa.html',
        controller: 'CursarEtapaController'
      }).
      when('/relatorio-etapa/:idCursoAluno/:idEtapa/:idEtapaAluno/:idRelatorio/:jogo/:aberto', {
        templateUrl: 'partials/relatorio/relatorio.html',
        controller: 'RelatorioEtapaController'
      }).
      when('/consultar-professor', {
        templateUrl: 'partials/professor/consultar/professor-consulta.html',
        controller: 'ProfessorConsultaController'
      }).
      when('/salvar-professor', {
        templateUrl: 'partials/professor/salvar/professor-salvar.html',
        controller: 'ProfessorSalvarController'
      }).
      when('/salvar-professor/:idProfessor', {
        templateUrl: 'partials/professor/salvar/professor-salvar.html',
        controller: 'ProfessorSalvarController'
      }).
      when('/consultar-categoria', {
        templateUrl: 'partials/categoria/consultar/categoria-consulta.html',
        controller: 'CategoriaConsultaController'
      }).
      when('/salvar-categoria', {
        templateUrl: 'partials/categoria/salvar/categoria-salvar.html',
        controller: 'CategoriaSalvarController'
      }).
      when('/salvar-categoria/:idCategoria', {
        templateUrl: 'partials/categoria/salvar/categoria-salvar.html',
        controller: 'CategoriaSalvarController'
      }).
      when('/usuario', {
        templateUrl: 'partials/usuario/usuario.html',
        controller: 'UsuarioController'
      }).
      when('/meus-andamento', {
        templateUrl: 'partials/meus-andamento/listagem.html',
        controller: 'MeusAndamentoController'
      }).

      otherwise({
        redirectTo: '/home'
      });
  }]);