(function() {
    'use strict';

    angular
        .module('user.core', [])
        .controller('UserController', controller);

    /** @ngInject */
    function controller($http, USERS_URL) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(USERS_URL)

            .success(function(result) {
                vm.users = result;
                vm.user = null;
                vm.userForm.$setPristine();
                vm.message = '';
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.initEdit = function(user) {
            vm.edit = true;
            vm.user = user;
            vm.userForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Update';
        };

        vm.initAddUser = function() {
            vm.edit = false;
            vm.user = null;
            vm.userForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Create';
        };

        vm.deleteUser = function(user) {
            $http.delete(USERS_URL + '/' + user.id)

            .success(function(result) {
                vm.deleteMessage = "Deleted User";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editUser = function(){
            $http.put(USERS_URL, vm.user)

            .success(function(result) {
                vm.user = null;
                vm.confirmPassword = null;
                vm.userForm.$setPristine();
                vm.message = "User Updated";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.addUser = function(){
            $http.post(USERS_URL, vm.user)

            .success(function(result) {
                vm.user = null;
                vm.confirmPassword = null;
                vm.userForm.$setPristine();
                vm.message = "User Created";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
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