(function() {
    'use strict';
    angular
        .module('shivaniCorrugationApp')
        .factory('Reel', Reel);

    Reel.$inject = ['$resource', 'DateUtils'];

    function Reel ($resource, DateUtils) {
        var resourceUrl =  'api/reels/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.receivedOn = DateUtils.convertLocalDateFromServer(data.receivedOn);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.receivedOn = DateUtils.convertLocalDateToServer(copy.receivedOn);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.receivedOn = DateUtils.convertLocalDateToServer(copy.receivedOn);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
