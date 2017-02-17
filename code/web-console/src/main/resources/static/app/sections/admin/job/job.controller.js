(function() {
    'use strict';

    angular
        .module('admin.job.core', [])
        .controller('AdminJobController', controller);

    /** @ngInject */
    function controller($http, ngToast, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL + '?status=NEW')

            .success(function(result) {
                vm.jobs = result;
            })

            .error(function(error) {
                 ngToast.danger(error.message);
            });
        };

        vm.approve = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/approve')

            .success(function(result) {
                ngToast.success("Job approved: " + job.title);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.reject = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/reject')

            .success(function(result) {
                ngToast.success("Job rejected: " + job.title);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.init();
    }
})();