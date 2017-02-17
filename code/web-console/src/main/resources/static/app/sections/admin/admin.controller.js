(function() {
    'use strict';

    angular
        .module('admin.core', [])
        .controller('AdminController', controller);

    /** @ngInject */
    function controller($http, $state) {
        $state.go('admin.jobs');
    }
})();