(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .factory('PaperMasterSearch', PaperMasterSearch);

    PaperMasterSearch.$inject = ['$resource'];

    function PaperMasterSearch($resource) {
        var resourceUrl =  'api/_search/paper-masters/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
