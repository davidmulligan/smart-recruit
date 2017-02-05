(function() {
    'use strict';

    angular
        .module('createjob.core', [])
        .controller('CreateJobController', controller);

    /** @ngInject */
    function controller($http, jobData) {
        var vm = this;
        vm.categories = jobData.categories;
        vm.skills = jobData.skills;

        vm.submit = function() {
            $http.post('http://localhost:8888/jobs', vm.job)

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