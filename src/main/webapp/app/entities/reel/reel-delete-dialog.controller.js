(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ReelDeleteController',ReelDeleteController);

    ReelDeleteController.$inject = ['$uibModalInstance', 'entity', 'Reel'];

    function ReelDeleteController($uibModalInstance, entity, Reel) {
        var vm = this;

        vm.reel = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Reel.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
