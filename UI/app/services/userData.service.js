'use strict';
var urls = {}

function userDataService($q, $http, URLS, $state, $rootScope, notificationService) {
    var service = {};
    service.getFields = function () {
        return $http.get(URLS.getFields);
    }
    return service;
}

angular.module('app').service('userDataService', ['$q', '$http', 'URLS', '$state', '$rootScope', 'notificationService', userDataService]);