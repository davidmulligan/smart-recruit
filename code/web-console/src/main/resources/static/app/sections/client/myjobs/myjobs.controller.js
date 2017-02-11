(function() {
    'use strict';

    angular
        .module('client.myjobs.core', [])
        .controller('ClientMyJobsController', controller);

    /** @ngInject */
    function controller($http, $uibModal, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL + '/self')

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
                templateUrl: 'app/sections/client/myjobs/myjobs_detail.html',
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

        vm.cancelJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/cancel')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.disputeJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/dispute')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.archiveJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/archive')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.init();
    }
})();