tccApp.service('AnexoService', ['$http', function ($http) {
    this.uploadFileToUrl = function (file, uploadUrl, nomeArquivo, uploadOK, uploadError) {
        var fd = new FormData();
        fd.append('file', file);
        fd.append('nomeArquivo', nomeArquivo);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
                .success(uploadOK)
                .error(uploadError);
    };
}]);