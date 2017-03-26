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

        vm.modalBidDetail = function(selectedBid, selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/account/jobs/bid_detail.html',
                controller: function($scope, $uibModalInstance, $uibModalStack, $log, Feedback, bid, job) {
                    $scope.bid = bid;
                    $scope.job = job;

                    Feedback.getAll({userId: $scope.bid.createdBy.id},
                        function(data) {
                            $scope.feedback = data;
                            $scope.ratingBreakdown = _.groupBy(data, "rating");
                            $log.info('Successfully fetched feedback for freelancer : ' + $scope.bid.createdBy.fullName);
                        }
                    );

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.bid);
                        $uibModalStack.dismissAll();
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

        vm.cancelJob = function(job) {
            Jobs.cancel({id: job.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
            });
        };

        vm.acceptBid = function(bid, job) {
            Jobs.acceptBid({id: job.id, bidId: bid.id}, function(result) {
                NotifyService.sendMessage('JobChangeEvent');
            });
        };

        vm.rejectBid = function(bid, job) {
            Jobs.rejectBid({id: job.id, bidId: bid.id}, function(result) {
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