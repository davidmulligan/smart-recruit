(function() {
    'use strict';

    angular
        .module('common.navigation.core', [])
        .controller('NavigationController', controller);

    /** @ngInject */
    function controller($state, NotifyService) {
        var vm = this;

    	vm.logout = function() {
    	    NotifyService.sendMessage('LogoutEvent');
    		$state.go('home');
    	};
    }
})();