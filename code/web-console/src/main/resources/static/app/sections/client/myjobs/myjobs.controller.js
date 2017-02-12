(function() {
    'use strict';

    angular
        .module('client.myjobs.core', [])
        .controller('ClientMyJobsController', controller);

    /** @ngInject */
    function controller($http, $uibModal, JOBS_URL, USERS_URL) {
        var vm = this;

        vm.hoveringOver = function(value) {
            vm.overStar = value;
        };

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

        vm.modalFreelancerFeedback = function(size, selectedJob, selectedUser) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/myjobs/feedback_freelancer.html',
                controller: function($scope, $uibModalInstance, job, user) {
                    $scope.job = job;
                    $scope.user = user;
                    $scope.feedback = {job: job, user: user};

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.job, $scope.user, $scope.feedback);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: size,
                resolve: {
                    job: function() {
                        return selectedJob;
                    },
                    user: function() {
                        return selectedUser;
                    }
                }
            });

            modalInstance.result.then(function(selectedItem) {}, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        vm.modalDispute = function(size, selectedJob) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/myjobs/dispute.html',
                controller: function($scope, $uibModalInstance, job) {
                    $scope.job = job;
                    $scope.dispute = {};

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.job, $scope.dispute);
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
                console.log(error.message);
            });
        };

        vm.disputeJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/dispute')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.archiveJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/archive')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.finishJob = function(job) {
            $http.put(JOBS_URL + '/' + job.id + '/finish')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.acceptApplication = function(job, application) {
            $http.put(JOBS_URL + '/' + job.id + '/applications/' + application.id + '/accept')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.saveFeedback = function(user, feedback) {
            $http.post(USERS_URL + '/' + user.id + '/feedback', feedback)

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.saveDispute = function(job, dispute) {
            $http.post(JOBS_URL + '/' + job.id + '/disputes', dispute)

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.init();
    }
})();