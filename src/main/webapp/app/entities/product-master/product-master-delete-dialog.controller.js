(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ProductMasterDeleteController',ProductMasterDeleteController);

    ProductMasterDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProductMaster'];

    function ProductMasterDeleteController($uibModalInstance, entity, ProductMaster) {
        var vm = this;

        vm.productMaster = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProductMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
