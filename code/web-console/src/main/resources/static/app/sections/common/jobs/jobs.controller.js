(function() {
    'use strict';

    angular
        .module('common.jobs.core', [])
        .controller('CommonJobsController', controller);

    /** @ngInject */
    function controller($uibModal, $log, ngToast, Categories, Skills, Jobs) {
        var vm = this;
        vm.isSearchOpen = true;

        vm.init = function() {
            vm.fetchCategories();
            vm.fetchSkills();
            vm.fetchJobs();
        };

        vm.fetchCategories = function() {
            Categories.getAll(
                function(data) {
                   vm.categories = data;
                   $log.info('Successfully fetched ' + data.length + ' categories.');
                }
            );
        };

        vm.fetchSkills = function() {
            Skills.getAll(
                function(data) {
                   vm.skills = data;
                   $log.info('Successfully fetched ' + data.length + ' skills.');
                }
            );
        };

        vm.fetchJobs = function() {
            Jobs.getAll({'status':'APPROVED'},
                function(data) {
                   vm.jobs = data;
                   $log.info('Successfully fetched ' + data.length + ' jobs.');
                }
            );
        };

        vm.modalBid = function(selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/common/jobs/job_bid.html',
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
            Jobs.createBid({id: job.id}, bid, function(result) {
                if (job.fixed) {
                    ngToast.success('Successfully applied for job.');
                } else {
                    ngToast.success('Successfully placed bid.');
                }
            });
        };

        vm.init();
    }
})();