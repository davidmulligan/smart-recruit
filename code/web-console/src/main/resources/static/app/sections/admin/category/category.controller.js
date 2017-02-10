(function() {
    'use strict';

    angular
        .module('admin.category.core', [])
        .controller('AdminCategoryController', controller);

    /** @ngInject */
    function controller($http, CATEGORIES_URL) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(CATEGORIES_URL)

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
            $http.delete(CATEGORIES_URL + '/' + category.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Category";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editCategory = function() {
            $http.put(CATEGORIES_URL, vm.category)

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
            $http.post(CATEGORIES_URL, vm.category)

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