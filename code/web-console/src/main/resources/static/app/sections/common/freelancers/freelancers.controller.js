(function() {
    'use strict';

    angular
        .module('common.freelancers.core', [])
        .controller('CommonFreelancersController', controller);

    /** @ngInject */
    function controller($http, $uibModal, $log, ngToast, USERS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(USERS_URL + '/freelancers')

            .success(function(result) {
                vm.freelancers = result;
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.modalDetail = function(size, selectedFreelancer) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/common/freelancers/freelancer_detail.html',
                controller: function($scope, $http, $uibModalInstance, USERS_URL, freelancer) {
                    $scope.freelancer = freelancer;

                    $http.get(USERS_URL + '/' + freelancer.id + '/feedback')
                    .success(function(result) {
                        $scope.feedback = result;
                        $scope.ratingBreakdown = _.groupBy(result, "rating");
                    })
                    .error(function(error) {
                        ngToast.danger(error.message);
                    });

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.freelancer);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: size,
                resolve: {
                    freelancer: function() {
                        return selectedFreelancer;
                    }
                }
            });

            modalInstance.result.then(function(selectedItem) {}, function() {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        vm.init();
    }
})();