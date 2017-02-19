(function() {
    'use strict';

    angular
        .module('common.home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller(Skills, Categories, $log) {
        var vm = this;

        vm.init = function() {
            vm.fetchSkills();
            vm.fetchCategories();
        };

        vm.fetchSkills = function() {
            Skills.principal(
                function(data) {
                   vm.skills = data;
                   $log.info('Successfully fetched ' + data.length + ' skills.');
                }
            );
        };

        vm.fetchCategories = function() {
            Categories.principal(
                function(data) {
                   vm.categories = data;
                   $log.info('Successfully fetched ' + data.length + ' categories.');
                }
            );
        }

        vm.init();
    }
})();