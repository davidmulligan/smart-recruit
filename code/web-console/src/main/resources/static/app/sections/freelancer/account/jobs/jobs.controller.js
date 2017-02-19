(function() {
    'use strict';

    angular
        .module('freelancer.account.jobs.core', [])
        .controller('FreelancerAccountJobsController', controller);

    /** @ngInject */
    function controller(Jobs, $log) {
        var vm = this;

        vm.init = function() {
            Jobs.self(
                function(data) {
                   vm.jobs = data;
                   $log.info('Successfully fetched ' + data.length + ' jobs.');
                }
            );
        };

        vm.init();
    }
})();