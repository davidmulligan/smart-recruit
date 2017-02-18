(function() {
    'use strict';

    angular
        .module('common.jobs.core', [])
        .controller('CommonJobsController', controller);

    /** @ngInject */
    function controller($http, $uibModal, $log, ngToast, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL + '?status=APPROVED')

            .success(function(result) {
                vm.jobs = result;
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.modalDetail = function(size, selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/common/jobs/job_detail.html',
                controller: function($scope, $uibModalInstance, job) {
                    $scope.job = job;

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.job);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: size,
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

        vm.bidJob = function(job, bid) {
            $http.post(JOBS_URL + '/' + job.id + '/bids', bid)

            .success(function(result) {
                ngToast.success('Successfully placed bid');
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.init();
    }
})();