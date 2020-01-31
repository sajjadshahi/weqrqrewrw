(function () {
    'use strict';

    angular
        .module('app')
        .controller('userDataController', userDataController)

    userDataController.$inject = ['userDataService', 'notificationService', 'dynamicInputService', '$http', 'URLS'];

    function userDataController(userDataService, notificationService, dynamicInputService, $http, URLS) {
        var vm = this;
        userDataService.getFields().then(function(response){
            vm.userFields = response.data.fields;
        });
        vm.initializeDynamicItem = function(item){
            dynamicInputService.initializeDynamicItem(item);
        }
        vm.saveFields = function(){
            var fields = dynamicInputService.processFields(vm.userFields);
            $http.post(URLS.saveMedicalRecord, {fields: fields});
        }
    }
})();