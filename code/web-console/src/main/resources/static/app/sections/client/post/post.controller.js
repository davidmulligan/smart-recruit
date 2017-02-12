(function() {
    'use strict';

    angular
        .module('client.post.core', [])
        .controller('ClientJobController', controller);

    /** @ngInject */
    function controller($http, JOBS_URL, referenceData) {
        var vm = this;
        vm.categories = referenceData.categories;
        vm.skills = referenceData.skills;

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