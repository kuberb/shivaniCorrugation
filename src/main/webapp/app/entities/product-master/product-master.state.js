(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('product-master', {
            parent: 'entity',
            url: '/product-master?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ProductMasters'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/product-master/product-masters.html',
                    controller: 'ProductMasterController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
            }
        })
        .state('product-master-detail', {
            parent: 'product-master',
            url: '/product-master/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ProductMaster'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/product-master/product-master-detail.html',
                    controller: 'ProductMasterDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ProductMaster', function($stateParams, ProductMaster) {
                    return ProductMaster.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'product-master',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('product-master-detail.edit', {
            parent: 'product-master-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-master/product-master-dialog.html',
                    controller: 'ProductMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProductMaster', function(ProductMaster) {
                            return ProductMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('product-master.new', {
            parent: 'product-master',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-master/product-master-dialog.html',
                    controller: 'ProductMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                productId: null,
                                productName: null,
                                productDescription: null,
                                customer: null,
                                type: null,
                                decal: null,
                                length: null,
                                width: null,
                                height: null,
                                reelSize: null,
                                sheetSize: null,
                                surfaceArea: null,
                                ply1: null,
                                ply2: null,
                                ply3: null,
                                ply4: null,
                                ply5: null,
                                ply1Weight: null,
                                ply2Weight: null,
                                ply3Weight: null,
                                ply4Weight: null,
                                ply5Weight: null,
                                plateQty: null,
                                partitionQty: null,
                                plateLength: null,
                                plateWidth: null,
                                partitionLength: null,
                                partitionWidth: null,
                                platePly1: null,
                                platePly2: null,
                                platePly3: null,
                                platePly4: null,
                                platePly5: null,
                                partitionPly1: null,
                                partitionPly2: null,
                                partitionPly3: null,
                                partitionPly4: null,
                                partitionPly5: null,
                                platePly1Weight: null,
                                platePly2Weight: null,
                                platePly3Weight: null,
                                platePly4Weight: null,
                                platePly5Weight: null,
                                partitionPly1Weight: null,
                                partitionPly2Weight: null,
                                partitionPly3Weight: null,
                                partitionPly4Weight: null,
                                partitionPly5Weight: null,
                                plateWeight: null,
                                partitionWeight: null,
                                boxWeight: null,
                                productWeight: null,
                                price: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('product-master', null, { reload: 'product-master' });
                }, function() {
                    $state.go('product-master');
                });
            }]
        })
        .state('product-master.edit', {
            parent: 'product-master',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-master/product-master-dialog.html',
                    controller: 'ProductMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ProductMaster', function(ProductMaster) {
                            return ProductMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('product-master', null, { reload: 'product-master' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('product-master.delete', {
            parent: 'product-master',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/product-master/product-master-delete-dialog.html',
                    controller: 'ProductMasterDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ProductMaster', function(ProductMaster) {
                            return ProductMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('product-master', null, { reload: 'product-master' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
