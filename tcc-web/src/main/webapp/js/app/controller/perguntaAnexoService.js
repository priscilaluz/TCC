tccApp.service('PerguntaAnexoService', ['$http', function ($http) {
    this.uploadFileToUrl = function (file, uploadUrl, parametros, uploadOK, uploadError) {
        var fd = new FormData();
        fd.append('file', file);
        fd.append('idPergunta', parametros);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
                .success(uploadOK)
                .error(uploadError);
    };
}]);