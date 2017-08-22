(function() {
    'use strict';
    angular
        .module('shivaniCorrugationApp')
        .factory('ProductMaster', ProductMaster);

    ProductMaster.$inject = ['$resource'];

    function ProductMaster ($resource) {
        var resourceUrl =  'api/product-masters/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
