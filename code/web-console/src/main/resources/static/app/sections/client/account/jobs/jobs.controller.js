(function() {
    'use strict';

    angular
        .module('client.account.jobs.core', [])
        .controller('ClientAccountJobsController', controller);

    /** @ngInject */
    function controller($uibModal, $log, NotifyService, Jobs) {
        var vm = this;

        vm.init = function() {
            Jobs.self(function(result) {
                vm.jobs = result;
            })
        };

        vm.modalJobDetail = function(selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/account/jobs/job_detail.html',
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

        vm.modalFreelancerDetail = function(selectedBid, selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/account/jobs/freelancer_detail.html',
                controller: function($scope, $uibModalInstance, $log, bid, job) {
                    $scope.bid = bid;
                    $scope.job = job;

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.bid);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: 'lg',
                resolve: {
                    bid: function() {
                        return selectedBid;
                    },
                    job: function() {
                        return selectedJob;
                    }
                }
            });

            modalInstance.result.then(function(selectedItem) {}, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        vm.acceptBid = function(bid, job) {
            Jobs.accept({id: job.id, bidId: bid.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
            });
        };

        vm.cancelJob = function(job) {
            Jobs.cancel({id: job.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
            });
        };

        vm.archiveJob = function(job) {
            Jobs.cancel({id: job.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
            });
        };

        NotifyService.getMessage('JobChangeEvent', function(event, data) {
            vm.init();
        });

        vm.init();
    }
})();