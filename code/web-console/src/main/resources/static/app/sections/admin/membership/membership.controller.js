(function() {
    'use strict';

    angular
        .module('admin.membership.core', [])
        .controller('AdminMembershipController', controller);

    /** @ngInject */
    function controller($http, MEMBERSHIPS_URL) {
        var vm = this;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(MEMBERSHIPS_URL)

            .success(function(result) {
                vm.memberships = result;
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.message = '';
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.initEdit = function(membership) {
            vm.edit = true;
            vm.membership = membership;
            vm.membershipForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Update';
        };

        vm.initAddMembership = function() {
            vm.edit = false;
            vm.membership = null;
            vm.membershipForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Create';
        };

        vm.deleteMembership = function(membership) {
            $http.delete(MEMBERSHIPS_URL + '/' + membership.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Membership";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editMembership = function() {
            $http.put(MEMBERSHIPS_URL, vm.membership)

            .success(function(result) {
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.message = "Membership Updated";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.addMembership = function() {
            $http.post(MEMBERSHIPS_URL, vm.membership)

            .success(function(result) {
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.message = "Membership Created";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.submit = function() {
            if (vm.edit){
                vm.editMembership();
            } else {
                vm.addMembership();
            }
        };

        vm.init();
    }
})();