(function() {
    'use strict';

    angular
        .module('common.register.core', [])
        .controller('RegisterController', controller);

    /** @ngInject */
    function controller($http, $state, ngToast, USERS_URL) {
        var vm = this;

        vm.submit = function() {
            $http.post(USERS_URL + '/register', vm.user)

            .success(function(result) {
                ngToast.success('Registration Successful');
                $state.go('home');
            })

            .error(function(error) {
                ngToast.danger('Error: ' + error.message);
            });
        };
    }
})();