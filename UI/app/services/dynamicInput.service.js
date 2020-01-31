angular.module('app')
    .service('dynamicInputService', ['$rootScope','Enum', dynamicInputServiceFunction]);

function dynamicInputServiceFunction($rootScope, Enum) {
    var service = this;
    var types = Enum.dynamicInputType;

    function hasThisid(item, id) {
        for (var i = 0; i < item.options.length; i++) {
            var field = item.options[i];
            if (id === field.id) {
                return true;
            }
        }
        return false;
    }
    service.initializeCheckbox = function(item){
        if (!item.required)
            item.isValid = true;
        else {
            item.isValid = false;
        }
        angular.forEach(item.value, function (val) {
            angular.forEach(item.options, function (opt) {
                if (opt.id == val) {
                    item.isValid = true;
                    opt.isSelected = true;
                }
            })
        })
        return item;
    }
    service.initializeDynamicItem = function (item) {
        item.copyValue = angular.copy(item.value);
        item.isDisabled = false;
        var type = item.type;

        switch (item.type) {
            case types.linearRadio:
            case types.multiLineRadio:
                if (item.value.length > 0) {
                    for (var i = 0; i < item.value.length; i++) {
                        item.value[i] = parseInt(item.value[i]);
                        if (hasThisid(item, item.value[i]) && item.isReadOnly) {
                            item.isDisabled = true;
                        }
                    }
                }
                break;
            case types.linearCheckbox:
            case types.multiLineCheckbox:
                if (item.value.length > 0) {
                    for (var i = 0; i < item.value.length; i++) {
                        item.value[i] = parseInt(item.value[i]);
                        if (hasThisid(item, item.value[i])) {
                            if (item.isReadOnly)
                                item.isDisabled = true;
                        } else {
                            item.value[0] = '';
                        }
                        for (var j = 0 ; j < item.options.length; j++){
                            if (item.options[j].id === item.value[i]){
                                item.options[j].isSelected = true;
                            }
                        }
                    }
                }
                break;


            case types.number:
            case types.currency:
                if (item.value[0]) {
                    item.value[0] = parseInt(item.value[0]);
                    if (item.isReadOnly) {
                        item.isDisabeld = true;
                    }
                }
                break;
            case types.select: {
                if (item.value[0] !== null && item.value[0] !== undefined) {
                    item.value[0] = parseInt(item.value[0]);
                    if (hasThisid(item, item.value[0]) && item.isReadOnly) {
                        item.isDisabled = true;
                    }
                } else {
                    item.value[0] = '';
                }
            }
                break;

            case types.textarea:
            case types.text:
                if (item.value[0] && item.isReadOnly) {
                    item.isDisabled = true;
                }

                break;

            case types.ajaxSelect: {
                item.isLoading = false;
                item.options = angular.copy($rootScope.searchSamples.initial);
            }
                break;
            case types.uploaderPlace: {
                if (item.value[0])
                    item.value = JSON.parse(item.value[0])
            }
        }
        item.initialized = true;
        return item;
    };
    service.processFields = function (fields, options) {
        var result = [], index = 0;
        options = options || {};
        options.error = options.error || {};
        fields = angular.copy(fields);
        angular.forEach(fields,
            function (item) {
                if (options.error) {
                    var error = false;
                    if (item.requier) {
                        switch (item.type) {
                            case types.uploaderPlace: {
                                if (!item.value.picture) {
                                    return;
                                }
                            }
                                break;
                            case types.linearRadio:
                            case types.multiLineRadio:
                            case types.linearCheckbox:
                            case types.multiLineCheckbox: {
                            }
                                break;
                            case types.text:
                            case types.textarea:
                            case types.currency: {
                                if (!item.value[0]) {
                                    throw {
                                        item: item,
                                        name: "field_" + item.id
                                    };
                                }
                            }
                                break;
                            default: {
                                if (item.value.length > 0) {
                                    if (item.value[0] === null || item.value[0] === undefined) {
                                        error = true;
                                    }
                                } else {
                                    error = true;
                                }
                                if (error)
                                    throw {
                                        item: item,
                                        name: "field_" + item.id
                                    };
                            }
                        }

                    }
                }

                var flag = true;
                switch (item.type) {
                    case types.linearRadio:
                    case types.multiLineRadio:
                        if (isNaN(item.value[0])) {
                            item.value = [];
                        }
                        if (item.requier && (item.value[0] === null || item.value[0] === undefined)) {
                            if (options.error) {
                                throw {
                                    type: "radio",
                                    item: item,
                                    name: "field_" + item.id
                                };
                            }
                        }
                        break;
                    case types.linearCheckbox:
                    case types.multiLineCheckbox:
                        // CHECKBOX
                        item.value = [];
                        angular.forEach(item.options,
                            function (option) {
                                if (option.isSelected === true) {
                                    item.value.push(option.id);
                                }
                            });
                        if (item.requier && !item.value) {
                            if (options.error) {
                                throw {
                                    type: "checkbox",
                                    item: item,
                                    name: "field_" + item.id
                                };
                            }

                        }
                        break;
                    // case types.number:
                    case types.currency:
                        if (item.value)
                            item.value[0] = strToNum(item.value[0]);
                        break;
                    case types.uploaderPlace: {
                        if (item.value.picture) {
                            item.value = [item.value.picture]
                        }
                    }
                        break;
                    case types.dateTime:
                    case types.date: {
                        if (item.value[1]) {
                            result.push({
                                id: item.id,
                                value: [item.value[1].unix]
                            });
                        }
                        flag = false;
                    }
                        break;
                    case types.select:
                    case types.textarea:
                    case types.text:
                    case types.ajaxSelect:
                        if (item.require) {

                        }
                        break;
                    case types.ckeditor: {
                        item.value[0] = CKEDITOR.instances['DYCK_' + item.id].getData();
                    }
                        break;
                }
                if (flag && item.value.length > 0 && item.value[0] !== null && item.value[0] !== undefined)
                    result.push({
                        id: item.id,
                        value: item.value
                    });
                if (options.onAfterProcess) {
                    options.onAfterProcess(item, result[index]);
                }
                index++;
            });
        return result;
    };
    return service;
}
