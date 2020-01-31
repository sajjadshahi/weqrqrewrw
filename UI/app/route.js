(function () {
    "use strict";

    angular.module("app").run(statesRun);

    statesRun.$inject = ["$rootScope", "$state"];

    function statesRun($rootScope, $state) {
        $rootScope.$state = $state;
        console.log($state);
        $rootScope.$on("$stateChangeSuccess", function () {
            window.scrollTo(0, 0);
        });
    }
})();

(function () {
    "use strict";

    angular.module("app").config(routesConfig);

    routesConfig.$inject = ["$stateProvider", "$urlRouterProvider"];

    function routesConfig($stateProvider, $urlRouterProvider) {
        // For unmatched routes
        $urlRouterProvider.otherwise("/");
        // Application routes
        $stateProvider
            .state("dash", {
                abstract: true,
                templateUrl: "app/layouts/dashboard.html"
            })
            .state("auth", {
                abstract: true,
                templateUrl: "app/layouts/auth.html"
            })

            .state("auth.auth", {
                templateUrl: "app/pages/auth/auth.html",
                url: "/auth",
                controller: "authController",
                controllerAs: "vm",
                resolve: {
                    deps: [
                        "$ocLazyLoad",
                        function ($ocLazyLoad) {
                            return $ocLazyLoad
                                .load([
                                    {
                                        name: "authService",
                                        files: ["app/services/auth.service.js"]
                                    }
                                ])
                                .then(function () {
                                    return $ocLazyLoad.load("app/pages/auth/auth.controller.js");
                                });
                        }
                    ]
                }
            }).state("dash.users", {
            templateUrl: "app/pages/users/users.html",
            url: "/users",
            controller: "usersController",
            controllerAs: "vm"
        }).state("dash.categories", {
            templateUrl: "app/pages/categories/categories.html",
            url: "/categories",
            controller: "categoriesController",
            controllerAs: "vm"
        })
            .state("dash.medium", {
                templateUrl: "app/pages/medium/medium.html",
                url: "/medium",
                controller: "mediumController",
                controllerAs: "vm"
            })
            .state("dash.notifications", {
                templateUrl: "app/pages/notifications/notifications.html",
                url: "/notifications",
                controller: "notificationController",
                controllerAs: "vm",
            })
            .state("dash.logs", {
                templateUrl: "app/pages/logs/logs.html",
                url: "/logs",
                controller: "logsController",
                controllerAs: "vm",
            }).state("dash.bulk", {
            templateUrl: "app/pages/qrcodes/bulk.html",
            url: "/bulk",
            controller: "qrcodeController",
            controllerAs: "vm",
        });
    }
})();
