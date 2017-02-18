(function() {
    'use strict';

    angular
        .module('client.account.job.core', [])
        .controller('ClientAccountJobController', controller);

    /** @ngInject */
    function controller($http, $uibModal, ngToast, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(JOBS_URL + '/self')

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
                templateUrl: 'app/sections/client/account/job/job_detail.html',
                controller: function($scope, $uibModalInstance, $log, job) {
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

        vm.finish = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/finish')

            .success(function(result) {
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.archive = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/archive')

            .success(function(result) {
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.cancel = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/cancel')

            .success(function(result) {
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.init();
    }
})();