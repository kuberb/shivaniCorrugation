(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('paper-master', {
            parent: 'entity',
            url: '/paper-master?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PaperMasters'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/paper-master/paper-masters.html',
                    controller: 'PaperMasterController',
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
        .state('paper-master-detail', {
            parent: 'paper-master',
            url: '/paper-master/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'PaperMaster'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/paper-master/paper-master-detail.html',
                    controller: 'PaperMasterDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'PaperMaster', function($stateParams, PaperMaster) {
                    return PaperMaster.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'paper-master',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('paper-master-detail.edit', {
            parent: 'paper-master-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/paper-master/paper-master-dialog.html',
                    controller: 'PaperMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaperMaster', function(PaperMaster) {
                            return PaperMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('paper-master.new', {
            parent: 'paper-master',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/paper-master/paper-master-dialog.html',
                    controller: 'PaperMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                description: null,
                                mill: null,
                                gsm: null,
                                bf: null,
                                price: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('paper-master', null, { reload: 'paper-master' });
                }, function() {
                    $state.go('paper-master');
                });
            }]
        })
        .state('paper-master.edit', {
            parent: 'paper-master',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/paper-master/paper-master-dialog.html',
                    controller: 'PaperMasterDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaperMaster', function(PaperMaster) {
                            return PaperMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('paper-master', null, { reload: 'paper-master' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('paper-master.delete', {
            parent: 'paper-master',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/paper-master/paper-master-delete-dialog.html',
                    controller: 'PaperMasterDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PaperMaster', function(PaperMaster) {
                            return PaperMaster.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('paper-master', null, { reload: 'paper-master' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
