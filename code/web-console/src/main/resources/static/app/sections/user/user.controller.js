(function() {
    'use strict';

    angular
        .module('user.core', [])
        .controller('UserController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get('http://localhost:8888/users')

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
            $http.delete('http://localhost:8888/users/' + user.id)

            .success(function(result) {
                vm.deleteMessage = "Deleted User";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editUser = function(){
            $http.put('http://localhost:8888/users', vm.user)

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
            $http.post('http://localhost:8888/users', vm.user)

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