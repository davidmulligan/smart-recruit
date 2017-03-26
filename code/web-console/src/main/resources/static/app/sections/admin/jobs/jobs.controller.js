(function() {
    'use strict';

    angular
        .module('admin.jobs.core', [])
        .controller('AdminJobsController', controller);

    /** @ngInject */
    function controller($uibModal, $log, ngToast, Jobs, NotifyService) {
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

        vm.modalJobDetail = function(selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/admin/jobs/job_detail.html',
                controller: function($scope, $uibModalInstance, $log, job) {
                    $scope.job = job;

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.job);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: 'lg',
                resolve: {
                    job: function() {
                        return selectedJob;
                    }
                }
            });

            modalInstance.result.then(function(selectedItem) {}, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
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