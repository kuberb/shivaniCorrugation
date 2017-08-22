(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('PaperMasterDialogController', PaperMasterDialogController);

    PaperMasterDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PaperMaster', 'ProductMaster'];

    function PaperMasterDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PaperMaster, ProductMaster) {
        var vm = this;

        vm.paperMaster = entity;
        vm.clear = clear;
        vm.save = save;
        vm.productmasters = ProductMaster.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.paperMaster.id !== null) {
                PaperMaster.update(vm.paperMaster, onSaveSuccess, onSaveError);
            } else {
                PaperMaster.save(vm.paperMaster, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('shivaniCorrugationApp:paperMasterUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
