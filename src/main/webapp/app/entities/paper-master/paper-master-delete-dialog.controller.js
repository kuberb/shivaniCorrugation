(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('PaperMasterDeleteController',PaperMasterDeleteController);

    PaperMasterDeleteController.$inject = ['$uibModalInstance', 'entity', 'PaperMaster'];

    function PaperMasterDeleteController($uibModalInstance, entity, PaperMaster) {
        var vm = this;

        vm.paperMaster = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PaperMaster.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
