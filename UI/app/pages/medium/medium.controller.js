"use strict";

function mediumControllerFunction($rootScope, tableService, URLS, $uibModal, notificationService, $state, FileUploader) {
    var vm = this;

    vm.medium = $rootScope.medium;
}

angular
    .module("app")
    .controller("mediumController", [
        "$rootScope",
        "tableService",
        "URLS",
        "$uibModal",
        "notificationService",
        "$state",
        "FileUploader",
        mediumControllerFunction
    ]);
