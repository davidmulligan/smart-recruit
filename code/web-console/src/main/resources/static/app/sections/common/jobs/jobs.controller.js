(function() {
    'use strict';

    angular
        .module('common.jobs.core', [])
        .controller('CommonJobsController', controller);

    /** @ngInject */
    function controller($uibModal, $log, ngToast, Jobs) {
        var vm = this;

        vm.init = function() {
            Jobs.getAll({'status':'APPROVED'}, function(result) {
                vm.jobs = result;
            })
        };

        vm.modalBid = function(selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/common/jobs/jobs_bid.html',
                controller: function($scope, $uibModalInstance, job) {
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

        vm.bid = function(job, bid) {
            Jobs.placeBid({id: job.id}, bid, function(result) {
                ngToast.success('Successfully placed bid.');
            });
        };

        vm.init();
    }
})();