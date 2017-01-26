(function() {
    'use strict';

    angular
        .module('login.core', [])
        .controller('LoginController', controller);

    /** @ngInject */
    function controller($state, AuthenticationService) {
        var vm = this;

        vm.login = function() {
            AuthenticationService.login({username: vm.username, password: vm.password})

                .then(function(result) {
                    vm.password = null;
                    if (AuthenticationService.isAuthenticated()) {
                        vm.message = '';
                        $state.go('home');
                    } else {
                        vm.message = 'Authentication Failed';
                    }
                })

                .catch(function() {
                    vm.message = 'Authentication Failed';
                });
    	};
    }
})();