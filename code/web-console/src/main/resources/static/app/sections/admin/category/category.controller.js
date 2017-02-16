(function() {
    'use strict';

    angular
        .module('admin.category.core', [])
        .controller('AdminCategoryController', controller);

    /** @ngInject */
    function controller($http, ngToast, CATEGORIES_URL) {
        var vm = this;
        vm.edit = false;
	    vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(CATEGORIES_URL)

            .success(function(result) {
                vm.categories = result;
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.initEdit = function(category) {
            vm.edit = true;
            vm.category = category;
            vm.categoryForm.$setPristine();
            vm.buttonText = 'Update';
        };

        vm.initCreate = function() {
            vm.edit = false;
            vm.category = null;
            vm.categoryForm.$setPristine();
            vm.buttonText = 'Create';
        };

        vm.deleteCategory = function(category) {
            $http.delete(CATEGORIES_URL + '/' + category.id)

            .success(function(result) {
                ngToast.info("Deleted category: " + category.name);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.editCategory = function() {
            $http.put(CATEGORIES_URL, vm.category)

            .success(function(result) {
                ngToast.info("Updated category: " + vm.category.name);
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.addCategory = function() {
            $http.post(CATEGORIES_URL, vm.category)

            .success(function(result) {
                ngToast.info("Created category: " + vm.category.name);
                vm.category = null;
                vm.categoryForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
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