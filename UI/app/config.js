(function () {
    "use strict";

    angular
        .module("app")
        .service("authInterceptor", [
            "$q",
            "$rootScope",
            function ($q, $rootScope) {
                var service = this;
                service.responseError = function (response) {
                    if (response.status === 401) {
                        $rootScope.$emit("unauthorized");
                    } else if (response.status === 403) {
                        $rootScope.$emit("accessDenied");
                    }
                    return $q.reject(response);
                };
            }
        ])
        .factory("httpRequestInterceptor", function () {
            return {
                request: function (config) {
                    if (localStorage.getItem("token")) {
                        config.headers["x-access-token"] =
                            localStorage.getItem("token");
                    }
                    return config;
                }
            };
        })
        .config([
            "$httpProvider", "ADMdtpProvider",
            function ($httpProvider, ADMdtpProvider) {
                $httpProvider.interceptors.push("authInterceptor");
                $httpProvider.interceptors.push("httpRequestInterceptor");
                ADMdtpProvider.setOptions({
                    calType: 'jalali',
                    format: 'YYYY/MM/DD hh:mm',
                    multiple: false
                });
            }
        ]);
})();
