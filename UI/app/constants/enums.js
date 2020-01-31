(function () {
    'use strict';
    angular
        .module('app')
        .constant('Enum', {
            domains: [
                "peidamishe.com",
                "shahi.me",
                "qr.shahi.me",
            ],
            dynamicInputType: {
                multiLineRadio: 1,
                linearRadio: 2,

                multiLineCheckbox: 3,
                linearCheckbox: 4,

                text: 5,
                number: 6,
                date: 7,
                dateTime: 18,
                currency: 8,
                textarea: 9,

                select: 10,

                ajaxSelect: 12,

                picture: 13,
                audio: 14,
                video: 15,
                file: 16,
                empty: 17,

                ckeditor: 11,

                uploaderPlace: 19
            }
        });
}());