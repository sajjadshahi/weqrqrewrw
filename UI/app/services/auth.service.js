'use strict';
var urls = {}

function authService($q, $http, URLS, $state, $rootScope, notificationService) {
    var service = {};

    service.getToken = function () {
        return localStorage.getItem("token");
    };
    service.checkLogin = function () {
        service.getUserInfo();
    };
    service.getUserInfo = function (success, error) {
        if (service.getToken()){
            success()
        }else{
            error();
        }
        return;
        return $http.get(URLS.userInfo).then(function (response) {
            service.setCredentials(response.data);
            if (response.data.role === 1) {
                $rootScope.isAdmin = true;
            }
            success(response);
        }, function (response) {
            service.clearCredentials();
            error(response);
        })
    };
    service.initCheck = function () {
        $rootScope.initShow = true;
        $state.go("dash.notifications");
        service.getUserInfo(function () {
            $rootScope.initShow = true;
        }, function () {
            $rootScope.initShow = true;
            $state.go("auth.auth");
        })
    };
    service.loginRequest = function (payload) {
        console.log(URLS.login)
        return $http.get(URLS.login, {
            params: payload
        })
    }
    service.registerRequest = function (payload) {
        return $http.post(URLS.register, payload)
    }
    service.login = function (payload) {
        return service.loginRequest(payload).then(function (response) {
            var data = response.data;
            if (!data.success) {
                notificationService.error({
                    text: data.message
                });
                return;
            }
            service.setCredentials(data);
            notificationService.success({
                text: 'شما با موفقیت وارد شدید.'
            })
            $state.go("dash.notifications")
        }, function (response) {
            response.data = response.data || {};
            notificationService.error({
                text: response.data.msg || 'There was an error while we tried to log you in.'
            })
        })
    };
    service.register = function (payload) {
        return service.registerRequest(payload).then(function (response) {
            service.setCredentials(response.data);
            notificationService.success({
                text: "ثبت نام شما با موفقیت انجام شد!"
            })
        }, function (response) {
            response.data = response.data || {};
            notificationService.error({
                text: response.data.msg || 'There was an error while we tried to log you in.'
            })
        })
    };
    service.setCredentials = function (data) {
        if (data.token) {
            localStorage.setItem("token", data.token);
            delete data.token;
        }
        localStorage.setItem("user", JSON.stringify(data));
        $rootScope.isLoggedIn = true;
        $rootScope.me = data;
    };
    service.clearCredentials = function () {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        $rootScope.isLoggedIn = false;
        $state.go("auth.auth")
    };
    service.logout = function () {
        return $http.post(URLS.logout).then(function () {
            service.clearCredentials();
            $rootScope.isLoggedIn = false;
            notificationService.success({
                text: 'You are successfully logged out'
            })
        });
    };
    return service;
}

angular.module('app').service('authService', ['$q', '$http', 'URLS', '$state', '$rootScope', 'notificationService', authService]);