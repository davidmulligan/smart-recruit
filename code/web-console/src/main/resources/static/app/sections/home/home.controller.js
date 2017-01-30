(function() {
    'use strict';

    angular
        .module('home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller($http, JOBS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get('http://localhost:8888/skills/principals')

            .success(function(result) {
                vm.skills = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.search = function() {
            $http.get(JOBS_URL + '?title=' + vm.searchTitle)

                .success(function(result) {
                    vm.jobs = result;
                    vm.message = '';
                })

                .error(function(error) {
                    vm.message = error.message;
                });
        }

        vm.init();
    }
})();