(function() {
    'use strict';

    angular
        .module('common.register.core', [])
        .controller('RegisterController', controller);

    /** @ngInject */
    function controller($http, $state, ngToast, Auth) {
        var vm = this;

        vm.submit = function() {
            Auth.post(vm.user,
                function(result) {
                    ngToast.success('Registration successful');
                    $state.go('home');
                }
            );
        };
    }
})();