(function() {
    'use strict';

    angular
        .module('admin.job.core', [])
        .controller('AdminJobController', controller);

    /** @ngInject */
    function controller($http, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL + '?status=PENDING')

            .success(function(result) {
                vm.jobs = result;
            })

            .error(function(error) {
                console.log(error);
            });
        };

        vm.approve = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/approve')

            .success(function(res) {
                vm.message = "Job Approved";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.reject = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/reject')

            .success(function(res) {
                vm.message = "Job Rejected";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.init();
    }
})();