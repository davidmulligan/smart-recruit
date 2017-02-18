(function() {
    'use strict';

    angular
        .module('client.post.core', [])
        .controller('ClientPostController', controller);

    /** @ngInject */
    function controller($http, ngToast, JOBS_URL, referenceData) {
        var vm = this;
        vm.categories = referenceData.categories;
        vm.skills = referenceData.skills;

        vm.submit = function() {
            $http.post(JOBS_URL, vm.job)

            .success(function(result) {
                ngToast.success("Job created: " + vm.job.title);
                vm.job = null;
                vm.createJobForm.$setPristine();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };
    }
})();