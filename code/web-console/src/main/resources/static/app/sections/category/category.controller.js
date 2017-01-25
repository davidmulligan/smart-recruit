(function() {
    'use strict';

    angular
        .module('category.core', [])
        .controller('CategoryController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get('http://localhost:8888/categories')

            .success(function(result) {
                vm.categories = result;
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.message = '';
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.initEdit = function(category) {
            vm.edit = true;
            vm.category = category;
            vm.categoryForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Update';
        };

        vm.initAddCategory = function() {
            vm.edit = false;
            vm.category = null;
            vm.categoryForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Create';
        };

        vm.deleteCategory = function(category) {
            $http.delete('http://localhost:8888/categories/' + category.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Category";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editCategory = function() {
            $http.put('http://localhost:8888/categories', vm.category)

            .success(function(result) {
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.message = "Category Updated";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.addCategory = function() {
            $http.post('http://localhost:8888/categories', vm.category)

            .success(function(result) {
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.message = "Category Created";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.submit = function() {
            if (vm.edit){
                vm.editCategory();
            } else {
                vm.addCategory();
            }
        };

	    vm.init();
    }
})();