(function() {
    'use strict';

    angular
        .module('home.core', [])
        .controller('HomeController', controller);

    /** @ngInject */
    function controller($http, SKILLS_URL) {
        var vm = this;

        vm.init = function() {
            $http.get(SKILLS_URL + '/principals')

            .success(function(result) {
                vm.skills = result;
            })

            .error(function(error) {
                console.log(error.message);
            });
        };

        vm.init();
    }
})();