tccApp.factory('RelatorioEtapa', ['$resource', function ($resource) {

        var RelatorioEtapa = $resource('/tcc/rest/curso-aluno/relatorioEtapa', {}, {
            save: {method: 'POST', params: {verbo: 'save'}, isArray: false}
        });

        return RelatorioEtapa;
}]);