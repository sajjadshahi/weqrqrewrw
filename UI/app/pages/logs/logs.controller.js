"use strict";


function logsControllerFunction($http, tableService, URLS, $uibModal, notificationService, $state, FileUploader) {
    var vm = this;

    vm.logs = tableService.tableFactory(
        {
            url: URLS.logsList,
            pagination: true
        },
        function (response) {

        }
    );
}

angular
    .module("app")
    .controller("logsController", [
        "$http",
        "tableService",
        "URLS",
        "$uibModal",
        "notificationService",
        "$state",
        "FileUploader",
        logsControllerFunction
    ]);
