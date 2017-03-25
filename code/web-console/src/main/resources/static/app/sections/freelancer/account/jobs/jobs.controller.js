(function() {
    'use strict';

    angular
        .module('freelancer.account.jobs.core', [])
        .controller('FreelancerAccountJobsController', controller);

    /** @ngInject */
    function controller($log, ngToast, Jobs, NotifyService) {
        var vm = this;

        vm.init = function() {
            Jobs.self(
                function(data) {
                   vm.jobs = data;
                   $log.info('Successfully fetched ' + data.length + ' jobs.');
                }
            );
        };

        vm.cancelBid = function(job, bid) {
            Jobs.cancelBid({id: job.id, bidId: bid.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
                ngToast.success("Bid cancelled: " + job.title);
            });
        };

        NotifyService.getMessage('JobChangeEvent', function(event, data) {
            vm.init();
        });

        vm.init();
    }
})();