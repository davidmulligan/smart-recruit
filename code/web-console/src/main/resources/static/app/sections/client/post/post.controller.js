(function() {
    'use strict';

    angular
        .module('client.post.core', [])
        .controller('ClientPostController', controller);

    /** @ngInject */
    function controller($http, ngToast, referenceData, Jobs) {
        var vm = this;
        vm.categories = referenceData.categories;
        vm.skills = referenceData.skills;

        vm.submit = function() {
            Jobs.post(vm.job,
                function(result) {
                    ngToast.success("Successfully posted job: " + vm.job.title);
                    vm.job = null;
                    vm.form.$setPristine();
                }
            );
        };
    }
})();