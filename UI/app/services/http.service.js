var httpService = function ($http, URLS) {
    var service = this;

    this.ngTableRequest = function (options, requestData) {
        var {count, page, params, sort} = options;
        var config = "";
        if (options.pagination) {
            config = '?size=' + count + '&page=' + page;
            if (Object.keys(params).length > 0) {
                config = config + '&' + params;
            }
        }
        if (sort) {
            config = (config.length > 0 ? '&' : '?') + 'sort=' + encodeURIComponent(sort);
        }
        return $http.get(requestData.url + config);
    };

    this.getProjects = function(){
        return $http.get(URLS.projects + "?noPaginate=true");
    }
    this.getCategories = function(){
        return $http.get(URLS.categories + "?noPaginate=true");
    }
};

httpService.$inject = ["$http", "URLS"];
angular.module("app").service("httpService", httpService);