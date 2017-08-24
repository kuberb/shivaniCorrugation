(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ReelDialogController', ReelDialogController);

    ReelDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Reel', 'PaperMaster'];

    function ReelDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Reel, PaperMaster) {
        var vm = this;

        vm.reel = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.papermasters = PaperMaster.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.reel.id !== null) {
                Reel.update(vm.reel, onSaveSuccess, onSaveError);
            } else {
                Reel.save(vm.reel, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('shivaniCorrugationApp:reelUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.receivedOn = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
