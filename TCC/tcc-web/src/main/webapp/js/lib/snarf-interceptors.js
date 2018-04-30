(function(angular) {
    "use strict";
    var snarfInterceptors = function (angular) {
        var snarfInterceptorsModule = angular.module("snarf.interceptors", []);

        snarfInterceptorsModule.factory('snRequestExceptionInterceptor', ['$rootScope', '$window', '$q', '$location', '$log',  function($rootScope, $window, $q, $location, $log) {
            var decrementRequestCount = function () {
                if (!$rootScope.snActiveRequestCount) {
                    $rootScope.snActiveRequestCount = 0;
                } else {
                    $rootScope.snActiveRequestCount--;
                    if ($rootScope.snActiveRequestCount<0) {
                        $rootScope.snActiveRequestCount = 0;
                    }
                }
            };

            var incrementRequestCount = function () {
                if (!$rootScope.snActiveRequestCount) {
                    $rootScope.snActiveRequestCount = 1;
                } else {
                    $rootScope.snActiveRequestCount += 1;
                }
            };

            var RequestExceptionInterceptor = {
                REQUEST_ERROR_EVENT: "snRequestException",
                request: function(config) {
                    incrementRequestCount();
                    return config;
                },
                response: function(response) {
                    decrementRequestCount();
                    return response;
                },
                responseError: function (error) {
                    decrementRequestCount();
                    var data = error.data || {message:"Sem mensagem do serviço", st:"No stack"};
                    if (data && data.message && data.message==="fcorp.exception.notauthenticated") {
                        $window.alert('A sua sessão expirou. Uma tentativa de reconexão será feita automaticamente.');
                        $location.path('#/');
                    } else {
                        var message = (data.message ? data.message : data) + "";
                        if (message.indexOf("Sessão informada não existe") < 0 ){
                            $rootScope.$broadcast(RequestExceptionInterceptor.REQUEST_ERROR_EVENT, { message: message.replace(/(<([^>]+)>)/ig), error: error, st: data.st });
                        }
                        if (data.st) {
                            $log.error(data.st);
                        } else {
                            $log.error(error);
                        }
                    }
                    $rootScope.lastError = error;
                    return $q.reject(error);
                }
            };

            return RequestExceptionInterceptor;
        }]);
    
        return snarfInterceptorsModule;
    };

    // Resgitro em interface AMD
    if ( typeof define === "function" && define.amd ) {
        define( "snarf-interceptors", ["angular"], function(angular) {
            return snarfInterceptors(angular);
        });
    } else {
        return snarfInterceptors(angular);
    }
    

})(window.angular);
