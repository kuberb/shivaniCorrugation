(function() {
    'use strict';

    angular
        .module('shivaniCorrugationApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('reel', {
            parent: 'entity',
            url: '/reel?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Reels'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/reel/reels.html',
                    controller: 'ReelController',
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
                }]
            }
        })
        .state('reel-detail', {
            parent: 'reel',
            url: '/reel/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Reel'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/reel/reel-detail.html',
                    controller: 'ReelDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Reel', function($stateParams, Reel) {
                    return Reel.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'reel',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('reel-detail.edit', {
            parent: 'reel-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reel/reel-dialog.html',
                    controller: 'ReelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Reel', function(Reel) {
                            return Reel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('reel.new', {
            parent: 'reel',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reel/reel-dialog.html',
                    controller: 'ReelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                reelNumber: null,
                                decal: null,
                                weight: null,
                                receivedOn: null,
                                lotNo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('reel', null, { reload: 'reel' });
                }, function() {
                    $state.go('reel');
                });
            }]
        })
        .state('reel.edit', {
            parent: 'reel',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reel/reel-dialog.html',
                    controller: 'ReelDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Reel', function(Reel) {
                            return Reel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('reel', null, { reload: 'reel' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('reel.delete', {
            parent: 'reel',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reel/reel-delete-dialog.html',
                    controller: 'ReelDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Reel', function(Reel) {
                            return Reel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('reel', null, { reload: 'reel' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
