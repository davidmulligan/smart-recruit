(function() {
    'use strict';

    angular
        .module('common.navigation.core', [])
        .controller('NavigationController', controller);

    /** @ngInject */
    function controller($rootScope, $state) {
        var vm = this;

    	vm.logout = function() {
            $rootScope.$broadcast('LogoutSuccessful');
    		$state.go('login');
    	};
    }
})();