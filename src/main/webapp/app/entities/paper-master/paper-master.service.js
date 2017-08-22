(function() {
    'use strict';
    angular
        .module('shivaniCorrugationApp')
        .factory('PaperMaster', PaperMaster);

    PaperMaster.$inject = ['$resource'];

    function PaperMaster ($resource) {
        var resourceUrl =  'api/paper-masters/:id';

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
