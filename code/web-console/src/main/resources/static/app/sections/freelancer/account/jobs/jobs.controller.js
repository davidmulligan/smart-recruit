(function() {
    'use strict';

    angular
        .module('freelancer.account.jobs.core', [])
        .controller('FreelancerAccountJobsController', controller);

    /** @ngInject */
    function controller($uibModal, $log, ngToast, Jobs, NotifyService) {
        var vm = this;

        vm.init = function() {
            Jobs.self(
                function(data) {
                   vm.jobs = data;
                   $log.info('Successfully fetched ' + data.length + ' jobs.');
                }
            );
        };

        vm.modalJobDetail = function(selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/freelancer/account/jobs/job_detail.html',
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

        vm.cancelBid = function(job, bid) {
            Jobs.cancelBid({id: job.id, bidId: bid.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
                ngToast.success("Bid cancelled: " + job.title);
            });
        };

        vm.confirmBid = function(job, bid) {
            Jobs.confirmBid({id: job.id, bidId: bid.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
                ngToast.success("Bid confirmed: " + job.title);
            });
        };

        NotifyService.getMessage('JobChangeEvent', function(event, data) {
            vm.init();
        });

        vm.init();
    }
})();