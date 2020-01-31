(function () {
    "use strict";

    angular.module("app", [
        // Angular modules
        "ui.router",
        "ui.bootstrap",
        "ui.select",
        "ngTable",
        "oc.lazyLoad",
        "angularFileUpload",
        // Custom modules
        'ADM-dateTimePicker'
        // 3rd Party Modules
    ]).run(runFunction);

    function runFunction($rootScope, authService, URLS, $state) {
        $rootScope.getQRCodeByIdUrl = function (id) {
            return `${URLS.getQRById}${id}`
        }
        $rootScope.getBulkZipDownloadUrl = function (id) {
            return `${URLS.downloadBulk}${id}`
        }
        $rootScope.logOut = function () {
            localStorage.removeItem("token");
            $state.go("auth.auth")
        }
        $rootScope.medium = [{
            id: 1,
            title: "صفحه نمایش آمفی تئاتر"
        }, {
            id: 2,
            title: "سامانه پیامکی دانشگاه"
        }, {
            id: 3,
            title: "بلندگوی راهرو"
        }, {
            id: 4,
            title: "ایمیل دانشگاه"
        }, {
            id: 5,
            title: "صفحه نمایش لابی"
        }];
        $rootScope.severities = [{
            id: 1,
            title: "شدت پایین"
        },{
            id: 2,
            title: "معمولی"
        },{
            id: 3,
            title: "شدت بالا"
        },{
            id: 4,
            title: "فوری!"
        }];
        $rootScope.getTitleById = function(array, id){
          return array.filter(function(o){
              return o.id === id
          })[0].title;
        };
        $rootScope.priorities = [{
            id: 1,
            title: "اولویت پایین"
        },{
            id: 2,
            title: "معمولی"
        },{
            id: 3,
            title: "اولویت بالا"
        },{
            id: 4,
            title: "مهم!"
        }]
        authService.initCheck();
    }

    runFunction.$inject = ['$rootScope', 'authService', 'URLS', '$state']
})();
