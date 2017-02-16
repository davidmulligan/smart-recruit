(function() {
    'use strict';

    angular
        .module('app.config', [
            'ngResource',
            'ngToast',
            'ngAnimate',
            'ui.router',
            'ui.bootstrap',
            'app.constants',
            'app.factories',
            'app.services',
            'app.module'
    ])
    .run(runner)
    .config(state)
    .config(toaster)
    .config(config)
    .config(resources);

    /* @ngInject */
    function runner($rootScope, $state, $stateParams, AuthenticationService, AccessTokenStorage) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.$on('LoginSuccessful', function() {
            $rootScope.currentUser = AuthenticationService.getCurrentUser();
    	});

    	$rootScope.$on('LogoutSuccessful', function() {
            $rootScope.currentUser = null;
    		AuthenticationService.clearCurrentUser();
    	});

        // The following method will run at the time of initializing the module, running once.
        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
            if (!AuthenticationService.isAuthenticated()) {
                if (toState.data.secure) {
                    if (AccessTokenStorage.retrieve()) {
                        AuthenticationService.recoverToken();
                    } else {
                        event.preventDefault();
                        $state.go('login');
                    }
                }
            } else {
                if (toState.data && toState.data.role) {
                    var hasAccess = false;
    				for (var i = 0; i < AuthenticationService.getCurrentUser().roles.length; i++) {
    					var role = AuthenticationService.getCurrentUser().roles[i];
    					if (toState.data.role === role) {
    						hasAccess = true;
    						break;
    					}
    				}
                    if (!hasAccess) {
                        event.preventDefault();
                        $state.go('access-denied');
                    }
                }
            }
        });
    }

    /* @ngInject */
    function state($stateProvider) {
        $stateProvider.linkState = angular.linkState;
    }

    /* @ngInject */
    function toaster(ngToastProvider) {
        ngToastProvider.configure({
            animation: 'fade',
            dismissButton : true
        });
    }

    /* @ngInject */
    function config($logProvider, $httpProvider) {
        $logProvider.debugEnabled(true);
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json';
        $httpProvider.defaults.headers.patch = {};
    }

    /** @ngInject */
    function resources($resourceProvider) {
        $resourceProvider.defaults.actions = {
            get: {
                method: 'GET'
            },
            getAll: {
                method: 'GET',
                isArray: true
            },
            post: {
                method: 'POST'
            },
            update: {
                method: 'PUT'
            },
            del: {
                method: 'DELETE'
            }
        };
    }
})();
