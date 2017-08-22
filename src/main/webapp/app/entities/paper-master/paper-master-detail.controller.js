(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('PaperMasterDetailController', PaperMasterDetailController);

    PaperMasterDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PaperMaster', 'ProductMaster'];

    function PaperMasterDetailController($scope, $rootScope, $stateParams, previousState, entity, PaperMaster, ProductMaster) {
        var vm = this;

        vm.paperMaster = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('shivaniCorrugationApp:paperMasterUpdate', function(event, result) {
            vm.paperMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
