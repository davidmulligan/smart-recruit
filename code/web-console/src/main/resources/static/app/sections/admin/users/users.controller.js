(function() {
    'use strict';

    angular
        .module('admin.users.core', [])
        .controller('AdminUsersController', controller);

    /** @ngInject */
    function controller($http, ngToast, USERS_URL) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(USERS_URL)

            .success(function(result) {
                vm.users = result;
                vm.user = null;
                vm.userForm.$setPristine();
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.initEdit = function(user) {
            vm.edit = true;
            vm.user = user;
            vm.userForm.$setPristine();
            vm.buttonText = 'Update';
        };

        vm.initCreate = function() {
            vm.edit = false;
            vm.user = null;
            vm.userForm.$setPristine();
            vm.buttonText = 'Create';
        };

        vm.deleteUser = function(user) {
            $http.delete(USERS_URL + '/' + user.id)

            .success(function(result) {
                ngToast.success("Deleted user: " + user.firstName + user.lastName);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.editUser = function(){
            $http.put(USERS_URL, vm.user)

            .success(function(result) {
                ngToast.success("Updated user: " + vm.user.firstName + vm.user.lastName);
                vm.user = null;
                vm.confirmPassword = null;
                vm.userForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.addUser = function(){
            $http.post(USERS_URL, vm.user)

            .success(function(result) {
                ngToast.success("Created user: " + vm.user.firstName + vm.user.lastName);
                vm.user = null;
                vm.confirmPassword = null;
                vm.userForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.submit = function() {
            if (vm.edit) {
                vm.editUser();
            } else{
                vm.addUser();
            }
        };

        vm.init();
    }
})();