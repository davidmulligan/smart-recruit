(function() {
    'use strict';

    angular
        .module('common.freelancers.core', [])
        .controller('CommonFreelancersController', controller);

    /** @ngInject */
    function controller($uibModal, $log, Users, referenceData) {
        var vm = this;
        vm.isSearchOpen = true;
        vm.skills = referenceData.skills;

        vm.search = function() {
            vm.freelancers = [];
            vm.isSearchOpen = false;
            Users.freelancers( {skill: vm.skill ? vm.skill.id : ''},
                function(data) {
                   vm.freelancers = data;
                   $log.info('Successfully fetched ' + data.length + ' freelancers.');
                }
            );
        };

        vm.modalDetail = function(selectedFreelancer) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/sections/common/freelancers/freelancer_detail.html',
                controller: function($scope, $uibModalInstance, Feedback, freelancer) {
                    $scope.freelancer = freelancer;

                    Feedback.getAll({userId: $scope.freelancer.id},
                        function(data) {
                            $scope.feedback = data;
                            $scope.ratingBreakdown = _.groupBy(data, "rating");
                            $log.info('Successfully fetched feedback for freelancer : ' + $scope.freelancer.fullName);
                        }
                    );

                    $scope.ok = function() {
                        $uibModalInstance.close($scope.freelancer);
                    };

                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                },
                size: 'lg',
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
    }
})();