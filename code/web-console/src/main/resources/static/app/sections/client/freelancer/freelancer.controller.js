(function() {
    'use strict';

    angular
        .module('client.freelancer.core', [])
        .controller('ClientFreelancerController', controller);

    /** @ngInject */
    function controller($http, $uibModal, USERS_URL, ModalService) {
        var vm = this;

        vm.init = function() {
            $http.get(USERS_URL + '/freelancers')

            .success(function(result) {
                vm.freelancers = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.modalDetail = function(size, selectedFreelancer) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/client/freelancer/freelancer_detail.html',
                controller: function($scope, $uibModalInstance, user) {
                    $scope.user = user;

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.user);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: size,
                resolve: {
                    user: function() {
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