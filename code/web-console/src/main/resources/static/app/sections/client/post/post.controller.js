(function() {
    'use strict';

    angular
        .module('client.post.core', [])
        .controller('ClientPostController', controller);

    /** @ngInject */
    function controller($log, ngToast, Categories, Jobs, Skills) {
        var vm = this;

         vm.init = function() {
            vm.fetchCategories();
            vm.fetchSkills();
        };

        vm.fetchCategories = function() {
            Categories.getAll(
                function(data) {
                   vm.categories = data;
                   $log.info('Successfully fetched ' + data.length + ' categories.');
                }
            );
        };

        vm.fetchSkills = function() {
            Skills.getAll(
                function(data) {
                   vm.skills = data;
                   $log.info('Successfully fetched ' + data.length + ' skills.');
                }
            );
        };

        vm.submit = function() {
            Jobs.post(vm.job,
                function(result) {
                    ngToast.success("Successfully posted job: " + vm.job.title);
                    vm.job = null;
                    vm.form.$setPristine();
                }
            );
        };

        vm.init();
    }
})();