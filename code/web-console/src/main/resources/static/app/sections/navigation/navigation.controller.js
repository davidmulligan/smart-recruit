(function() {
    'use strict';

    angular
        .module('navigation.core', [])
        .controller('NavigationController', controller);

    /** @ngInject */
    function controller($state, $rootScope, AuthenticationService) {

        $rootScope.$on('LoginSuccessful', function() {
            $rootScope.user = AuthenticationService.getCurrentUser();
    	});

    	$rootScope.$on('LogoutSuccessful', function() {
    	    $rootScope.user = null;
    		AuthenticationService.clearCurrentUser();
    	});

    	$rootScope.logout = function() {
    		$rootScope.$broadcast('LogoutSuccessful');
    		$state.go('login');
    	};
    }
})();