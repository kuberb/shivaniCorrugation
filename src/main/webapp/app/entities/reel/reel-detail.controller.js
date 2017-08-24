(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ReelDetailController', ReelDetailController);

    ReelDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Reel', 'PaperMaster'];

    function ReelDetailController($scope, $rootScope, $stateParams, previousState, entity, Reel, PaperMaster) {
        var vm = this;

        vm.reel = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('shivaniCorrugationApp:reelUpdate', function(event, result) {
            vm.reel = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
