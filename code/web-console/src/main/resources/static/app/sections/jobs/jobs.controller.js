(function() {
    'use strict';

    angular
        .module('jobs.core', [])
        .controller('JobsController', controller);

    /** @ngInject */
    function controller($http, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL)

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