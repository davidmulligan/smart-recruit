(function() {
    'use strict';

    angular
        .module('common.login.core', [])
        .controller('LoginController', controller);

    /** @ngInject */
    function controller($state, ngToast, AuthenticationService) {
        var vm = this;

        vm.login = function() {
            AuthenticationService.login({username: vm.email, password: vm.password}).then(function(result) {
                vm.password = null;
                if (AuthenticationService.isAuthenticated()) {
                    $state.go('home');
                } else {
                    ngToast.danger('Please check you have entered your email address and password correctly and try again.');
                }
            })
            .catch(function() {
                ngToast.danger('Please check you have entered your email address and password correctly and try again.');
            });
    	};
    }
})();