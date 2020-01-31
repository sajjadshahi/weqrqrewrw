"use strict";

function usersControllerFunction($http, tableService, URLS, $uibModal, notificationService, $state, FileUploader) {
    var vm = this;

    vm.users = tableService.tableFactory(
        {
            url: URLS.usersList,
            pagination: true
        },
        function (response) {

        }
    );
}

angular
    .module("app")
    .controller("usersController", [
        "$http",
        "tableService",
        "URLS",
        "$uibModal",
        "notificationService",
        "$state",
        "FileUploader",
        usersControllerFunction
    ]);
