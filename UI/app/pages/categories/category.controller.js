"use strict";

function categoriesControllerFunction($http, tableService, URLS, $uibModal, notificationService) {
    var vm = this;
    vm.categories = tableService.tableFactory(
        {
            url: URLS.categories,
            pagination: true
        },
        function (response) {

        }
    );
    vm.filterQRByCategory = function (row) {
        $state.go("dash.qrcodes", {categoryId: row.id})
    };

    vm.createNewProject = function () {
        $uibModal.open({
            templateUrl: "app/pages/categories/newCategory.html",
            controller: function ($uibModalInstance) {
                var mc = this;
                mc.cancel = function () {
                    $uibModalInstance.dismiss();
                };

                mc.submit = function () {
                    $http.post(URLS.createNewCategory, mc.formData).then(function (response) {
                        mc.isCreating = false;
                        notificationService.success(
                            " دسته‌بندی جدید ساخته شد."
                        );
                        mc.cancel();
                        vm.categories.reload();
                    });
                };
            },
            controllerAs: "mc"
        });
    }
}

angular
    .module("app")
    .controller("categoriesController", [
        "$http",
        "tableService",
        "URLS",
        "$uibModal",
        "notificationService",
        categoriesControllerFunction
    ]);
