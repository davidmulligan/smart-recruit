(function() {
    'use strict';

    angular
        .module('admin.job.core', [])
        .controller('AdminJobController', controller);

    /** @ngInject */
    function controller($log, ngToast, Jobs, NotifyService) {
        var vm = this;

        vm.init = function() {
            vm.fetchJobs();
        };

       vm.fetchJobs = function() {
           Jobs.getAll({'status':'NEW'},
               function(data) {
                  vm.jobs = data;
                  $log.info('Successfully fetched ' + data.length + ' jobs.');
               }
           );
       };

        vm.approve = function(job) {
            Jobs.approve({id: job.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
                ngToast.success("Job approved: " + job.title);
            });
        };

        vm.reject = function(job) {
            Jobs.reject({id: job.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
                ngToast.success("Job rejected: " + job.title);
            });
        };

        NotifyService.getMessage('JobChangeEvent', function(event, data) {
            vm.init();
        });

        vm.init();
    }
})();