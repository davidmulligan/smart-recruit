(function() {
    'use strict';

    angular
        .module('client.job.core', [])
        .controller('ClientJobController', controller);

    /** @ngInject */
    function controller($http, JOBS_URL, jobData) {
        var vm = this;
        vm.categories = jobData.categories;
        vm.skills = jobData.skills;

        vm.submit = function() {
            $http.post(JOBS_URL, vm.job)

            .success(function(result) {
                vm.job = null;
                vm.createJobForm.$setPristine();
                vm.message = "Job Created";
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };
    }
})();