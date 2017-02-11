(function() {
    'use strict';

    angular
        .module('freelancer.job.core', [])
        .controller('FreelancerJobController', controller);

    /** @ngInject */
    function controller($http, $uibModal, JOBS_URL) {
        var vm = this;
        vm.application = {};

        vm.init = function() {
            $http.get(JOBS_URL)

            .success(function(result) {
                vm.jobs = result;
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.modalDetail = function(size, selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/freelancer/job/job_detail.html',
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

        vm.applyJob = function(job) {
            $http.post(JOBS_URL + '/' + job.id + '/applications', vm.application)

            .success(function(result) {
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.bidJob = function(job) {
            $http.post(JOBS_URL + '/' + job.id + '/bids', vm.bid)

            .success(function(result) {
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.init();
    }
})();