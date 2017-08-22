(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .factory('ProductMasterSearch', ProductMasterSearch);

    ProductMasterSearch.$inject = ['$resource'];

    function ProductMasterSearch($resource) {
        var resourceUrl =  'api/_search/product-masters/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
