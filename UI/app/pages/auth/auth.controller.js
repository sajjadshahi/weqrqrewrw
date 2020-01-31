(function(){
    'use strict';

    angular
        .module('app')
        .controller('authController', authController)

    authController.$inject = ['authService', 'notificationService'];

    function authController(authService, notificationService) {
        var vm = this;
        vm.formData = {}
        vm.login = function () {
            var j = {
                mobile: vm.formData.mobile,
                password: vm.formData.password
            };
            authService.login(j).then(function () {
            })
        }
        vm.register = function () {
            if (vm.formData.agree){
                var j = {
                    mobile: vm.formData.mobile,
                    password: vm.formData.password,
                    name: vm.formData.name,
                }
                authService.register(j).then(function () {

                })
            }else{
                notificationService.error({
                    text: "لطفا با قوانین و مقررات موافقت کنید."
                })
            }

        }
    }
})();