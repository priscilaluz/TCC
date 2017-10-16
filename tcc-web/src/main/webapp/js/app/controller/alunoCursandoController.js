tccApp.controller('AlunoCursandoController', ['$scope', '$rootScope', '$routeParams', 'CursoAluno',
    function ($scope, $rootScope, $routeParams, CursoAluno) {
        $scope.model = {
            tabuleiroCurso: null
        };
        
        $scope.entrarNaFase = function (etapa) {
            
        };

        $scope.mouseover = function (index, coluna) {
            if (coluna === 1) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagemOn:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagemDesabilitado;
            }
            if (coluna === 2) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagemOn:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagemDesabilitado;
            }
            if (coluna === 3) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagemOn:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagemDesabilitado;
            }
            if (coluna === 4) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagemOn:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagemDesabilitado;
            }
            if (coluna === 5) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagemOn:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagemDesabilitado;
            }
        };

        $scope.mouseleave = function (index, coluna) {
            if (coluna === 1) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagemOff:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa1.imagemDesabilitado;
            }
            if (coluna === 2) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagemOff:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa2.imagemDesabilitado;
            }
            if (coluna === 3) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagemOff:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa3.imagemDesabilitado;
            }
            if (coluna === 4) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagemOff:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa4.imagemDesabilitado;
            }
            if (coluna === 5) {
                $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagem = 
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.desbloquada?
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagemOff:
                        $scope.model.tabuleiroCurso.tdEtapas[index].etapa5.imagemDesabilitado;
            }
        };
        var init = function () {
            $rootScope.appLoaded = false;
            CursoAluno.buscarCursoAlunoPorIdCursoAluno({'idCursoAluno': $routeParams.id}).$promise.then(function (tabuleiroCurso) {
                $scope.model.tabuleiroCurso = tabuleiroCurso;
                $rootScope.appLoaded = true;
            }, function (error) {
                $rootScope.appLoaded = true;
            });
            
        };
        init();
    }]);