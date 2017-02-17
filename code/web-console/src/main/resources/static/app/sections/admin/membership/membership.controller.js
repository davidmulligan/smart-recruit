(function() {
    'use strict';

    angular
        .module('admin.membership.core', [])
        .controller('AdminMembershipController', controller);

    /** @ngInject */
    function controller($http, ngToast, MEMBERSHIPS_URL) {
        var vm = this;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(MEMBERSHIPS_URL)

            .success(function(result) {
                vm.memberships = result;
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.initEdit = function(membership) {
            vm.edit = true;
            vm.membership = membership;
            vm.membershipForm.$setPristine();
            vm.buttonText = 'Update';
        };

        vm.initCreate = function() {
            vm.edit = false;
            vm.membership = null;
            vm.membershipForm.$setPristine();
            vm.buttonText = 'Create';
        };

        vm.deleteMembership = function(membership) {
            $http.delete(MEMBERSHIPS_URL + '/' + membership.id)

            .success(function(result) {
                ngToast.success("Deleted membership: " + membership.name);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.editMembership = function() {
            $http.put(MEMBERSHIPS_URL, vm.membership)

            .success(function(result) {
            ngToast.success("Updated membership: " + vm.membership.name);
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.addMembership = function() {
            $http.post(MEMBERSHIPS_URL, vm.membership)

            .success(function(result) {
                ngToast.success("Created membership: " + vm.membership.name);
                vm.membership = null;
                vm.membershipForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
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