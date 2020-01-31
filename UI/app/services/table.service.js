var tableService = function (NgTableParams, httpService) {
    var service = this;
    this.tableFactory = function (options, cb) {
        return new NgTableParams({
            page: 1,
            count: 10
        }, {
            counts: options.counts || [],
            getData: function (params) {
                options.loading = true;
                var page = params.page() - 1,
                    count = params.count(),
                    sorting = params.sorting(),
                    filters = params.filter(),
                    qs = '',
                    sort, key = Object.keys(sorting);
                if (sorting[key]) {
                    sort = (sorting[key] === 'desc' ? '-' : '+') + key;
                }
                if (options.getFilters){
                    filters = {...filters, ...options.getFilters()}
                }
                var filterKeys = Object.keys(filters);

                if (options.pagination){
                    angular.forEach(filterKeys, function (value, index) {
                        var filter = filters[value];
                        if (filter != "") {
                            qs = qs + value;
                            if (filters[value].length > 0) {
                                qs = qs + '=' + encodeURIComponent(filter);
                            }
                            if (index + 1 < filterKeys.length) {
                                qs = qs + '&';
                            }
                        }
                    });
                }
                var extraData = {};
                //TODO: Add Loading
                return httpService.ngTableRequest({
                    count,
                    page,
                    sort,
                    pagination: options.pagination,
                    params: qs
                }, {
                    url: options.url
                }).then(function (response) {
                    angular.forEach(response.data.data, function(d){
                        if (options.process){
                            d = options.process(d);
                        }
                    });
                    if (cb)
                        cb(response);
                    options.loading = false;
                    params.total(response.data.entity.length);
                    return response.data.entity;
                })

            }
        });
    }


};

tableService.$inject = ["NgTableParams", "httpService"];
angular.module("app").service("tableService", tableService);