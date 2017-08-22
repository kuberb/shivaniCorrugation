(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ProductMasterDetailController', ProductMasterDetailController);

    ProductMasterDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProductMaster', 'PaperMaster'];

    function ProductMasterDetailController($scope, $rootScope, $stateParams, previousState, entity, ProductMaster, PaperMaster) {
        var vm = this;

        vm.productMaster = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('shivaniCorrugationApp:productMasterUpdate', function(event, result) {
            vm.productMaster = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
