(function() {
    'use strict';

    angular
        .module('common.home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller($log, Categories, Jobs, Skills) {
        var vm = this;

        vm.init = function() {
            vm.fetchCategories();
            vm.fetchSkills();
            vm.fetchJobs();
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

        vm.fetchJobs = function() {
            Jobs.getAll({'status':'APPROVED'},
                function(data) {
                   vm.jobs = data;
                   $log.info('Successfully fetched ' + data.length + ' jobs.');
                }
            );
        };

        vm.init();
    }
})();