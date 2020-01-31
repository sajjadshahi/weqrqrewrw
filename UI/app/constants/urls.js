(function(){
    'use strict';
    var root = "http://localhost:8591/rest/user/";
    angular
        .module('app')
        .constant('URLS', {
            apiUrl : root,
            register: root + 'register',
            login: root + 'login',
            usersList: root + 'users',
            logsList: root + 'logs',
            userInfo: root + 'admin/me',
            projects: root + 'admin/project/list',
            createNewProject: root + 'admin/project/create',
            projectLogoUpload: root + 'admin/project/logo',
            categories: root + 'admin/category/list',
            createNewCategory: root + 'admin/category/create',
            notifications: root + 'notifications',
            performNotification: root + 'notification/perform',
            cancelNotification: root + 'notification/cancel',
            createNotification: root + 'notification/create',
        });
}());