(function() {
    'use strict';

    angular
        .module('common.home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller($http, ngToast, SKILLS_URL, CATEGORIES_URL) {
        var vm = this;

        vm.init = function() {
            vm.fetchSkills();
            vm.fetchCategories();
        };

        vm.fetchSkills = function() {
            $http.get(SKILLS_URL + '/principal')

            .success(function(result) {
                vm.skills = result;
            })

            .error(function(error) {
                ngToast.danger('Error: ' + error.message);
            });
        };

        vm.fetchCategories = function() {
            $http.get(CATEGORIES_URL + '/principal')

            .success(function(result) {
                vm.categories = result;
            })

            .error(function(error) {
                ngToast.danger('Error: ' + error.message);
            });
        }

        vm.init();
    }
})();