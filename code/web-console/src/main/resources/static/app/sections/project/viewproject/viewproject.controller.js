(function() {
    'use strict';

    angular
        .module('viewproject.core', [])
        .controller('ViewProjectController', controller);

    /** @ngInject */
    function controller($state, $http) {
        var vm = this;
        vm.selectedProjectId = $state.params.selectedProject;

        vm.init = function() {
            $http.get('http://localhost:8888/jobs/' + vm.selectedProjectId)

            .success(function(result) {
                vm.project = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.init();
    }
})();