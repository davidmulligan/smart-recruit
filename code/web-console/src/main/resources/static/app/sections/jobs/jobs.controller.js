(function() {
    'use strict';

    angular
        .module('jobs.core', [])
        .controller('JobsController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;

        vm.init = function() {
            $http.get('http://localhost:8888/projects')

            .success(function(result) {
                vm.jobs = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.init();
    }
})();