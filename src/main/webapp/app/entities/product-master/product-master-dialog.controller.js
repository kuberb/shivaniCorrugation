(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .controller('ProductMasterDialogController', ProductMasterDialogController);

    ProductMasterDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProductMaster', 'PaperMaster'];

    function ProductMasterDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProductMaster, PaperMaster) {
        var vm = this;

        vm.productMaster = entity;
        vm.clear = clear;
        vm.save = save;
        vm.papermasters = PaperMaster.query();
        vm.productMaster.plateSurfaceArea = 0;
        vm.productMaster.partitionSurfaceArea = 0;
        vm.productMaster.price = 0;
        
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if(vm.productMaster.type){
            vm.productMaster.ply1=vm.productMaster.ply1.id;
            vm.productMaster.ply2=vm.productMaster.ply2.id;
            vm.productMaster.ply3=vm.productMaster.ply3.id;
            }
            if(vm.productMaster.type=='FIVEPLY'){
            vm.productMaster.ply4=vm.productMaster.ply4.id;
            vm.productMaster.ply5=vm.productMaster.ply5.id;}
            if(vm.productMaster.plateQty){
            	vm.productMaster.platePly1=vm.productMaster.platePly1.id;
                vm.productMaster.platePly2=vm.productMaster.platePly2.id;
                vm.productMaster.platePly3=vm.productMaster.platePly3.id;
                if(vm.productMaster.platePly5){
                vm.productMaster.platePly4=vm.productMaster.platePly4.id;
                vm.productMaster.platePly5=vm.productMaster.platePly5.id;}
            }
            if(vm.productMaster.partitionQty){
            	vm.productMaster.partitionPly1=vm.productMaster.partitionPly1.id;
                vm.productMaster.partitionPly2=vm.productMaster.partitionPly2.id;
                vm.productMaster.partitionPly3=vm.productMaster.partitionPly3.id;
                if(vm.productMaster.partitionPly5){
                vm.productMaster.partitionPly4=vm.productMaster.partitionPly4.id;
                vm.productMaster.partitionPly5=vm.productMaster.partitionPly5.id;}
            }
            if (vm.productMaster.id !== null) {
                ProductMaster.update(vm.productMaster, onSaveSuccess, onSaveError);
            } else {
                ProductMaster.save(vm.productMaster, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('shivaniCorrugationApp:productMasterUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        $scope.dim = function() {
        	vm.productMaster.reelSize=(vm.productMaster.width + vm.productMaster.height + 4) /1000;
        	vm.productMaster.sheetSize=(vm.productMaster.width + vm.productMaster.length + 17.5) /500;
        	vm.productMaster.surfaceArea=vm.productMaster.reelSize*vm.productMaster.sheetSize;
        	$scope.ply();
        };
        
        $scope.ply = function() {
        	
        	if(vm.productMaster.ply1){
        	vm.productMaster.ply1Weight= vm.productMaster.surfaceArea * vm.productMaster.ply1.gsm;
        	}
        	if(vm.productMaster.ply2){
        	vm.productMaster.ply2Weight= 1.5 * (vm.productMaster.surfaceArea * vm.productMaster.ply2.gsm);
        	}
        	if(vm.productMaster.ply3){
        	vm.productMaster.ply3Weight= vm.productMaster.surfaceArea * vm.productMaster.ply3.gsm;
        	}
        	if(vm.productMaster.ply4){
        	vm.productMaster.ply4Weight= 1.5 * (vm.productMaster.surfaceArea * vm.productMaster.ply4.gsm);
        	}
        	if(vm.productMaster.ply5){
        	vm.productMaster.ply5Weight= vm.productMaster.surfaceArea * vm.productMaster.ply5.gsm;
        	}
        	vm.productMaster.ply1Weight=Math.trunc(vm.productMaster.ply1Weight);
        	vm.productMaster.ply2Weight=Math.trunc(vm.productMaster.ply2Weight);
        	vm.productMaster.ply3Weight=Math.trunc(vm.productMaster.ply3Weight);
        	vm.productMaster.ply4Weight=Math.trunc(vm.productMaster.ply4Weight);
        	vm.productMaster.ply5Weight=Math.trunc(vm.productMaster.ply5Weight);
        	vm.productMaster.boxWeight = vm.productMaster.ply1Weight + vm.productMaster.ply2Weight + vm.productMaster.ply3Weight + vm.productMaster.ply4Weight + vm.productMaster.ply5Weight;
        	if(vm.productMaster.type=='THREEPLY')
        		{
        		vm.productMaster.boxWeight = Math.trunc(vm.productMaster.boxWeight * 1.03);	
        		}
        	if(vm.productMaster.type=='FIVEPLY')
    			{
    			vm.productMaster.boxWeight = Math.trunc(vm.productMaster.boxWeight * 1.05);
    			}
        		vm.productMaster.productWeight = vm.productMaster.boxWeight;
        	if(vm.productMaster.plateWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + vm.productMaster.plateWeight;
        	}
        	if(vm.productMaster.partitionWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + vm.productMaster.partitionWeight;
        	}
        	
        	$scope.price();
        };
        
        $scope.platedim = function() {
        	vm.productMaster.plateSurfaceArea = vm.productMaster.plateLength * vm.productMaster.plateWidth/1000000;
        	$scope.plateply();
        };
        
        $scope.partitiondim = function() {
        	vm.productMaster.partitionSurfaceArea= vm.productMaster.partitionLength * vm.productMaster.partitionWidth/1000000;
        	$scope.partitionply();
        };
    
        $scope.plateply = function() {
        	if(vm.productMaster.platePly1){
        	vm.productMaster.platePly1Weight= vm.productMaster.plateSurfaceArea * vm.productMaster.platePly1.gsm;
        	}
        	if(vm.productMaster.platePly2){
        	vm.productMaster.platePly2Weight= 1.5 * (vm.productMaster.plateSurfaceArea * vm.productMaster.platePly2.gsm);
        	}
        	if(vm.productMaster.platePly3){
        	vm.productMaster.platePly3Weight= vm.productMaster.plateSurfaceArea * vm.productMaster.platePly3.gsm;
        	}
        	if(vm.productMaster.platePly4){
        	vm.productMaster.platePly4Weight= 1.5 * (vm.productMaster.plateSurfaceArea * vm.productMaster.platePly4.gsm);
        	}
        	if(vm.productMaster.platePly5){
        	vm.productMaster.platePly5Weight= vm.productMaster.plateSurfaceArea * vm.productMaster.platePly5.gsm;
        	}
        	vm.productMaster.plateWeight = vm.productMaster.platePly1Weight + vm.productMaster.platePly2Weight + vm.productMaster.platePly3Weight + vm.productMaster.platePly4Weight + vm.productMaster.platePly5Weight;
        	vm.productMaster.productWeight =  vm.productMaster.boxWeight;
        	if(vm.productMaster.plateWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + (vm.productMaster.plateWeight*vm.productMaster.plateQty);
        	}
        	if(vm.productMaster.partitionWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + (vm.productMaster.partitionWeight*vm.productMaster.partitionQty);
        	}
        	$scope.price();
        };
        
        $scope.partitionply = function() {
        	if(vm.productMaster.partitionPly1){
        	vm.productMaster.partitionPly1Weight= vm.productMaster.partitionSurfaceArea * vm.productMaster.partitionPly1.gsm;
        	}
        	if(vm.productMaster.partitionPly2){
        	vm.productMaster.partitionPly2Weight= 1.5 * (vm.productMaster.partitionSurfaceArea * vm.productMaster.partitionPly2.gsm);
        	}
        	if(vm.productMaster.partitionPly3){
        	vm.productMaster.partitionPly3Weight= vm.productMaster.partitionSurfaceArea * vm.productMaster.partitionPly3.gsm;
        	}
        	if(vm.productMaster.partitionPly4){
        	vm.productMaster.partitionPly4Weight= 1.5 * (vm.productMaster.partitionSurfaceArea * vm.productMaster.partitionPly4.gsm);
        	}
        	if(vm.productMaster.partitionPly5){
        	vm.productMaster.partitionPly5Weight= vm.productMaster.partitionSurfaceArea * vm.productMaster.partitionPly5.gsm;
        	}
        	vm.productMaster.partitionWeight = vm.productMaster.partitionPly1Weight + vm.productMaster.partitionPly2Weight + vm.productMaster.partitionPly3Weight + vm.productMaster.partitionPly4Weight + vm.productMaster.partitionPly5Weight;
        	vm.productMaster.productWeight = vm.productMaster.boxWeight;
        	if(vm.productMaster.plateWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + (vm.productMaster.plateWeight*vm.productMaster.plateQty);
        	}
        	if(vm.productMaster.partitionWeight){
        		vm.productMaster.productWeight = vm.productMaster.productWeight + (vm.productMaster.partitionWeight*vm.productMaster.partitionQty);
        	}
        	$scope.price();
        };
        
        $scope.price = function() {
        	vm.productMaster.price = 0;
        	if(vm.productMaster.ply1){
            		vm.productMaster.ply1Price = ((vm.productMaster.ply1Weight/1000) * vm.productMaster.ply1.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.ply1Price;
            	} 
            	if(vm.productMaster.ply2){
            		vm.productMaster.ply2Price = ((vm.productMaster.ply2Weight/1000) * vm.productMaster.ply2.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.ply2Price;
            	} 
            	if(vm.productMaster.ply3){
            		vm.productMaster.ply3Price = ((vm.productMaster.ply3Weight/1000) * vm.productMaster.ply3.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.ply3Price;
            	} 
            	if(vm.productMaster.ply4){
            		vm.productMaster.ply4Price = ((vm.productMaster.ply4Weight/1000) * vm.productMaster.ply4.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.ply4Price;
            	} 
            	if(vm.productMaster.ply5){
            		vm.productMaster.ply5Price = ((vm.productMaster.ply5Weight/1000) * vm.productMaster.ply5.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.ply5Price;
            	} 
            	if(vm.productMaster.platePly1){
            		vm.productMaster.platePly1Price = ((vm.productMaster.platePly1Weight/1000) * vm.productMaster.platePly1.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.plateQty * vm.productMaster.platePly1Price;
            		}
                if(vm.productMaster.platePly2){
                	vm.productMaster.platePly2Price = ((vm.productMaster.platePly2Weight/1000) * vm.productMaster.platePly2.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.plateQty * vm.productMaster.platePly2Price;
                	}
                if(vm.productMaster.platePly3){
                	vm.productMaster.platePly3Price = ((vm.productMaster.platePly3Weight/1000) * vm.productMaster.platePly3.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.plateQty * vm.productMaster.platePly3Price;
                	}
                if(vm.productMaster.platePly4){
                	vm.productMaster.platePly4Price = ((vm.productMaster.platePly4Weight/1000) * vm.productMaster.platePly4.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.plateQty * vm.productMaster.platePly4Price;
                	}
                if(vm.productMaster.platePly5){
                	vm.productMaster.platePly5Price = ((vm.productMaster.platePly5Weight/1000) * vm.productMaster.platePly5.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.plateQty * vm.productMaster.platePly5Price;
                	}
                if(vm.productMaster.partitionPly1){
            		vm.productMaster.partitionPly1Price = ((vm.productMaster.partitionPly1Weight/1000) * vm.productMaster.partitionPly1.price);
            		vm.productMaster.price = vm.productMaster.price + vm.productMaster.partitionQty * vm.productMaster.partitionPly1Price;
                }
                if(vm.productMaster.partitionPly2){
                	vm.productMaster.partitionPly2Price = ((vm.productMaster.partitionPly2Weight/1000) * vm.productMaster.partitionPly2.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.partitionQty * vm.productMaster.partitionPly2Price;
                }
                if(vm.productMaster.partitionPly3){
                	vm.productMaster.partitionPly3Price = ((vm.productMaster.partitionPly3Weight/1000) * vm.productMaster.partitionPly3.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.partitionQty * vm.productMaster.partitionPly3Price;
                }
                if(vm.productMaster.partitionPly4){
                	vm.productMaster.partitionPly4Price = ((vm.productMaster.partitionPly4Weight/1000) * vm.productMaster.partitionPly4.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.partitionQty * vm.productMaster.partitionPly4Price;
                }
                if(vm.productMaster.partitionPly5){
                	vm.productMaster.partitionPly5Price = ((vm.productMaster.partitionPly5Weight/1000) * vm.productMaster.partitionPly5.price);
                	vm.productMaster.price = vm.productMaster.price + vm.productMaster.partitionQty * vm.productMaster.partitionPly5Price;
                }
        };
        
    }
})();
