(function() {
    'use strict';

    angular
        .module('freelancers.core', [])
        .controller('FreelancersController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;

        vm.init = function() {
            $http.get('http://localhost:8888/users/freelancers')

            .success(function(result) {
                vm.freelancers = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.init();
    }
})();