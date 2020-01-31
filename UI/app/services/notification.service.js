function notify(data){
    if (typeof data == 'string'){
        data = {
            text: data
        }
    }
    $.notify({
        title: data.title,
        message: data.text,
    },{
        element: 'body',
        type: data.type,
        allow_dismiss: true,
        placement: {
            from: 'top',
            align: 'right'
        },
        offset: {
            x: 30,
            y: 30
        },
        spacing: 10,
        z_index: 1031,
        delay: 2500,
        timer: 1000,
        url_target: '_blank',
        mouse_over: false,
        animate: {
            enter: 'animated fadeInUp',
            exit: 'fadeOutDown'
        },
        template:   '<div data-notify="container" class="alert alert-dismissible alert-{0}" role="alert">' +
            '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>' +
            '<span data-notify="icon"></span> ' +
            '<span data-notify="title">{1}</span> ' +
            '<span data-notify="message">{2}</span>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
    });
}

function notificationService($uibModal){

    var service = {};
    service.success = function(options){
        options.type = 'success';
        return notify(options)
    }
    service.info = function(options){
        options.type = 'info';
        return notify(options)
    }
    service.warning = function(options){
        options.type = 'warning';
        return notify(options)
    }
    service.error = function(options){
        options.type = 'danger';
        return notify(options)
    }
    service.default = function(options){
        options.type = '-light';
        return notify(options)
    }
    service.confirm = function(options){
        options = options || {};
        $uibModal.open({
            templateUrl: "app/includes/modals/confirmation-modal.html",
            controller: function($uibModalInstance){
                var mc = this;
                mc.options = options;
                mc.cancel = function(){
                    $uibModalInstance.dismiss();
                }
                mc.submit = function(){
                    options.onSubmit();
                    mc.cancel();
                }
            },
            size: options.size || 'sm',
            controllerAs:'mc'
        })
    }
    return service;
}

angular.module('app').service('notificationService', ['$uibModal',notificationService]);