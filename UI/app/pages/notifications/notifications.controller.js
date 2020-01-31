"use strict";

function notificationControllerFunction(
    notificationService,
    tableService,
    httpService,
    URLS,
    $http,
    $uibModal,
    Enum,
    $q,
    $scope,
    $stateParams
) {
    var vm = this;
    vm.notifications = null;
    vm.currentFilters = {
        dateKey: 'createdAt'
    };
    if ($stateParams.projectId) {
        vm.currentFilters.projectId = $stateParams.projectId;
    }
    if ($stateParams.categoryId) {
        vm.currentFilters.categoryId = $stateParams.categoryId;
    }
    vm.domainsFilter = [{id: '', title: 'همه'}].concat(Enum.domains.map(function (d) {
        return {
            id: d,
            title: d
        }
    }));
    vm.notifications = tableService.tableFactory(
        {
            url: URLS.notifications,
            pagination: true,
            getFilters: function () {
                return vm.currentFilters;
            }
        },
        function (response) {
            vm.totalData = response.data.total;

        }
    );
    vm.performNotification = function(notification){
        $http.get(URLS.performNotification, {
            params: {
                id: notification.id
            }
        }).then(function(response){
            notificationService.success(response.data.entity)
        })
    }
    vm.cancelNotification = function(notification){
        $http.get(URLS.cancelNotification, {
            params: {
                id: notification.id
            }
        }).then(function(response){
            notificationService.success(response.data.entity)
        })
    }
    vm.dateFilter = function (key, value) {
        vm.currentFilters[key] = value.toString();
        vm.notifications.reload();
    }
    vm.projectSelected = function (project) {
        vm.currentFilters.projectId = project.id.toString();
        vm.notifications.reload();
    };
    vm.categorySelected = function (category) {
        vm.currentFilters.categoryId = category.id.toString();
        vm.notifications.reload();
    };
    vm.bulknotifications = tableService.tableFactory(
        {
            url: URLS.bulknotifications,
            pagination: true,
            getFilters: function () {
                return vm.currentFilters;
            }
        },
        function (response) {

        }
    );
    vm.createNewNotification = function (notification) {
        $uibModal.open({
            templateUrl: "app/pages/notifications/newNotification.html",
            controller: function ($uibModalInstance) {
                var mc = this;
                mc.cancel = function () {
                    $uibModalInstance.dismiss();
                };
                mc.formData = {};
                mc.submit = function () {
                    $http.get(URLS.createNotification, {
                        params:mc.formData
                    }).then(function (response) {
                        if (mc.formData.id) {
                            notificationService.success("کد با موفقیت ویرایش شد.")
                        } else {
                            notificationService.success("اعلان با موفقیت ایجاد شد.");
                        }
                        mc.notificationId = mc.formData.id || response.data.id;
                        mc.notificationText = response.data.code;
                        vm.notifications.reload();
                        mc.cancel()
                    });
                };
            },
            controllerAs: "mc"
        });
    };
    vm.createBulknotifications = function () {
        $uibModal.open({
            templateUrl: "app/pages/notifications/newBulknotifications.html",
            controller: function ($uibModalInstance) {
                var mc = this;
                mc.cancel = function () {
                    $uibModalInstance.dismiss();
                };
                mc.domains = angular.copy(Enum.domains);
                mc.projects = vm.projectsList;
                mc.categories = vm.categoriesList;
                mc.submit = function () {
                    if (!mc.formData.projectId) {
                        notificationService.error("پروژه را مشخص نکردید.");
                        return;
                    }
                    notificationService.info(
                        "لیست تجمیعی notification ها در حال ایجاد است."
                    );
                    mc.isCreating = true;
                    $http.post(URLS.createBulknotifications, mc.formData).then(function (response) {
                        mc.isCreating = false;
                        notificationService.success(
                            "فایل قابل دریافت است."
                        );
                        mc.cancel();
                        vm.bulknotifications.reload();
                    });
                };
            },
            controllerAs: "mc"
        });
    };
    vm.filters = {
        printedAt: [
            {
                title: "پرینت‌شده",
                id: 1
            },
            {
                title: "همه",
                isSelected: true,
                id: 0
            },
            {
                title: "پرینت‌نشده",
                id: -1
            }
        ],
        hasUserData: [
            {
                title: "اطلاعات تکمیل",
                id: 1
            },
            {
                title: "همه",
                isSelected: true,
                id: 0
            },
            {
                title: "بدون اطلاعات",
                id: -1
            }
        ],
        isVerified: [
            {
                title: "اطلاعات تاییدشده",
                id: 1
            },
            {
                title: "همه",
                isSelected: true,
                id: 0
            },
            {
                title: "اطلاعات تاییدنشده",
                id: -1
            }
        ]
    };
    vm.setFilter = function (key, value) {
        vm.currentFilters[key] = value.toString();
        angular.forEach(vm.filters[key], function (f) {
            f.isSelected = false;
            if (f.id === value) {
                f.isSelected = true;
            }
            return f;
        });
        vm.notifications.reload();
    };
    vm.printQR = function (qr) {
        notificationService.confirm({
            title: "چاپ بارکد",
            text: `آیا از چاپ این بارکد به کد ${qr.code} اطمینان دارید؟`,
            onSubmit: function () {
                return $http
                    .post(URLS.printnotification, {
                        id: qr.id
                    })
                    .then(function () {
                        vm.notifications.reload();
                    });
            }
        });
    };
    vm.verifyUserData = function (qr) {
        notificationService.confirm({
            title: "تایید اطلاعات کاربری",
            text: `آیا از تایید اطلاعات کاربری بارکد به کد ${qr.code} اطمینان دارید؟`,
            onSubmit: function () {
                return $http
                    .post(URLS.verifynotification, {
                        id: qr.id
                    })
                    .then(function () {
                        vm.notifications.reload();
                    });
            }
        });
    };
}

notificationControllerFunction.$inject = [
    "notificationService",
    "tableService",
    "httpService",
    "URLS",
    "$http",
    "$uibModal",
    "Enum", "$q", "$scope", "$stateParams"];
angular
    .module("app")
    .controller("notificationController",
        notificationControllerFunction
    );
