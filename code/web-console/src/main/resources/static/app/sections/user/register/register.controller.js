(function() {
    'use strict';

    angular
        .module('register.core', [])
        .controller('RegisterController', controller);

    /** @ngInject */
    function controller($http, AuthenticationService) {
        var vm = this;

        vm.submit = function() {
            $http.post('http://localhost:8888/auth/register', vm.user)

            .success(function(res) {
                vm.user = null;
                vm.confirmPassword = null;
                vm.registerForm.$setPristine();
                vm.message = "Registration Successful";
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };
    }
})();