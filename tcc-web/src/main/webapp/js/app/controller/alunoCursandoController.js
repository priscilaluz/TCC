tccApp.controller('AlunoCursandoController', ['$scope', '$rootScope', '$routeParams', '$location', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, $location, CursoAluno) {
        $scope.model = {
            tabuleiroCurso: null,
            paginaAtual: null,
            pagina: null
        };
        var idCursoAluno = $routeParams.id;
        
        $scope.anterior = function () {
            $scope.model.pagina = $scope.model.pagina-1;
            $scope.model.paginaAtual = $scope.model.tabuleiroCurso.tabuleiros[$scope.model.pagina];
        };
        
        $scope.proximo = function () {
            $scope.model.pagina = $scope.model.pagina+1;
            $scope.model.paginaAtual = $scope.model.tabuleiroCurso.tabuleiros[$scope.model.pagina];
        };
        
        $scope.entrarNaFase = function (etapa) {
            if (etapa.desbloquada) {
                $rootScope.appLoaded = false;
                CursoAluno.salvarEtapaAluno({'idCursoAluno': idCursoAluno, 'idEtapa': etapa.idEtapa}).$promise.then(function (etapaAluno) {
                    $rootScope.appLoaded = true;
                    $location.path("/cursar-etapa/"+idCursoAluno+"/"+etapa.idEtapa+"/"+etapaAluno.id);
                }, function (error) {
                    $rootScope.appLoaded = true;
                });
            }
        };

        $scope.mouseover = function (index, coluna) {
            if (coluna === 1) {
                $scope.model.paginaAtual[index].etapa1.imagem = 
                        $scope.model.paginaAtual[index].etapa1.desbloquada?
                        $scope.model.paginaAtual[index].etapa1.imagemOn:
                        $scope.model.paginaAtual[index].etapa1.imagemDesabilitado;
            }
            if (coluna === 2) {
                $scope.model.paginaAtual[index].etapa2.imagem = 
                        $scope.model.paginaAtual[index].etapa2.desbloquada?
                        $scope.model.paginaAtual[index].etapa2.imagemOn:
                        $scope.model.paginaAtual[index].etapa2.imagemDesabilitado;
            }
            if (coluna === 3) {
                $scope.model.paginaAtual[index].etapa3.imagem = 
                        $scope.model.paginaAtual[index].etapa3.desbloquada?
                        $scope.model.paginaAtual[index].etapa3.imagemOn:
                        $scope.model.paginaAtual[index].etapa3.imagemDesabilitado;
            }
            if (coluna === 4) {
                $scope.model.paginaAtual[index].etapa4.imagem = 
                        $scope.model.paginaAtual[index].etapa4.desbloquada?
                        $scope.model.paginaAtual[index].etapa4.imagemOn:
                        $scope.model.paginaAtual[index].etapa4.imagemDesabilitado;
            }
            if (coluna === 5) {
                $scope.model.paginaAtual[index].etapa5.imagem = 
                        $scope.model.paginaAtual[index].etapa5.desbloquada?
                        $scope.model.paginaAtual[index].etapa5.imagemOn:
                        $scope.model.paginaAtual[index].etapa5.imagemDesabilitado;
            }
        };

        $scope.mouseleave = function (index, coluna) {
            if (coluna === 1) {
                $scope.model.paginaAtual[index].etapa1.imagem = 
                        $scope.model.paginaAtual[index].etapa1.desbloquada?
                        $scope.model.paginaAtual[index].etapa1.imagemOff:
                        $scope.model.paginaAtual[index].etapa1.imagemDesabilitado;
            }
            if (coluna === 2) {
                $scope.model.paginaAtual[index].etapa2.imagem = 
                        $scope.model.paginaAtual[index].etapa2.desbloquada?
                        $scope.model.paginaAtual[index].etapa2.imagemOff:
                        $scope.model.paginaAtual[index].etapa2.imagemDesabilitado;
            }
            if (coluna === 3) {
                $scope.model.paginaAtual[index].etapa3.imagem = 
                        $scope.model.paginaAtual[index].etapa3.desbloquada?
                        $scope.model.paginaAtual[index].etapa3.imagemOff:
                        $scope.model.paginaAtual[index].etapa3.imagemDesabilitado;
            }
            if (coluna === 4) {
                $scope.model.paginaAtual[index].etapa4.imagem = 
                        $scope.model.paginaAtual[index].etapa4.desbloquada?
                        $scope.model.paginaAtual[index].etapa4.imagemOff:
                        $scope.model.paginaAtual[index].etapa4.imagemDesabilitado;
            }
            if (coluna === 5) {
                $scope.model.paginaAtual[index].etapa5.imagem = 
                        $scope.model.paginaAtual[index].etapa5.desbloquada?
                        $scope.model.paginaAtual[index].etapa5.imagemOff:
                        $scope.model.paginaAtual[index].etapa5.imagemDesabilitado;
            }
        };
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarCursoAlunoPorIdCursoAluno({'idCursoAluno': idCursoAluno}).$promise.then(function (tabuleiroCurso) {
                $scope.model.tabuleiroCurso = tabuleiroCurso;
                var pagina = tabuleiroCurso.etapaAtual/15;
                $scope.model.pagina = Math.ceil(pagina);
                $scope.model.paginaAtual = $scope.model.tabuleiroCurso.tabuleiros[$scope.model.pagina];
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
            
        };
        init();
    }]);