(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .factory('ReelSearch', ReelSearch);

    ReelSearch.$inject = ['$resource'];

    function ReelSearch($resource) {
        var resourceUrl =  'api/_search/reels/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
