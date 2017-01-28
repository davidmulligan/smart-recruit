(function() {
    'use strict';

    angular
        .module('home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;

        vm.search = function() {
            $http.get('http://localhost:8888/projects?title=' + vm.searchTitle)

                .success(function(result) {
                    vm.projects = result;
                    vm.message = '';
                })

                .error(function(error) {
                    vm.message = error.message;
                });

        }
    }
})();