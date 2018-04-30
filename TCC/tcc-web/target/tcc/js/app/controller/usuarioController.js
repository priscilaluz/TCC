tccApp.controller('UsuarioController', ['$scope', '$rootScope', 'Usuario', 'Enums', '$cookies', 'growl',
    function ($scope, $rootScope, Usuario, Enums, $cookies, growl) {
        $rootScope.telaHomeAluno = false;
        $scope.usuario = new Usuario();
        $scope.backgroundAvatar = [];
        var backgroundAvatarOriginal = {"background": "linear-gradient(#58a287, #c3dac1, #e1f7df)"};
        var backgroundAvatarSelecionado = {"background": "linear-gradient(#0e8056, #3aa931, #e1f7df)"};
        var backgroundAvatarPassarPor = {"background": "linear-gradient(#2b9c73, #81d07b, #e1f7df)"};
        
        $scope.mouseover = function (index) {
            if (!$scope.usuario.avatar || $scope.avatares[index].id !== $scope.usuario.avatar.id) {
                $scope.backgroundAvatar[index] = backgroundAvatarPassarPor;
            }
        };
        
        $scope.mouseleave = function (index) {
            if (!$scope.usuario.avatar || $scope.avatares[index].id !== $scope.usuario.avatar.id) {
                $scope.backgroundAvatar[index] = backgroundAvatarOriginal;
            }
        };
        
        $scope.selecionarAvatar = function (index) {
            for (var i = 0; i < $scope.avatares.length; i++) {
                $scope.backgroundAvatar[i] = backgroundAvatarOriginal;
            }
            $scope.backgroundAvatar[index] = backgroundAvatarSelecionado;
            $scope.usuario.avatar = $scope.avatares[index];
        };
        
        $scope.salvar = function () {
            $rootScope.appLoaded = false;
            $scope.usuario.$save(function () {
                growl.success('Alteração salva com sucesso.',{title: 'Sucesso'});
                
                $rootScope.usuarioLogado.nome = $scope.usuario.nome;
                $rootScope.usuarioLogado.senha = $scope.usuario.senha;
                $rootScope.usuarioLogado.avatar = $scope.usuario.avatar;
                
                $cookies.remove('usuarioLogado');
                $cookies.putObject('usuarioLogado', $rootScope.usuarioLogado);
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
        };
        
        var init = function () {
            $scope.usuario.id = $rootScope.usuarioLogado.id;
            $scope.usuario.login = $rootScope.usuarioLogado.login;
            $scope.usuario.email = $rootScope.usuarioLogado.email;
            $scope.usuario.nome = $rootScope.usuarioLogado.nome;
            $scope.usuario.senha = $rootScope.usuarioLogado.senha;
            $scope.usuario.avatar = $rootScope.usuarioLogado.avatar;
            $scope.usuario.tipo = $rootScope.usuarioLogado.tipo;
            
            $rootScope.appLoaded = false;
            Enums.getAvatares(function (avatares) {
                $rootScope.appLoaded = true;
                $scope.avatares = avatares;
                for (var i = 0; i < avatares.length; i++) {
                    if ($scope.avatares[i].id !== $scope.usuario.avatar.id) {
                        $scope.backgroundAvatar.push(backgroundAvatarOriginal);
                    } else {
                        $scope.backgroundAvatar.push(backgroundAvatarSelecionado);
                    }
                }
            }, function (error) {$rootScope.appLoaded = true;});
        };
        init();

    }]);