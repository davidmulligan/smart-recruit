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

        vm.applyJob = function(jobId) {
            $http.post('http://localhost:8888/jobs/' + jobId + '/bids', vm.bid)

            .success(function(result) {
//                vm.job = null;
//                vm.createJobForm.$setPristine();
//                vm.message = "Bid Submitted";
            })

            .error(function(error) {
                vm.message = error.message;
            });
        }

        vm.init();
    }
})();