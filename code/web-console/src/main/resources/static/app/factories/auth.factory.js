(function() {
    'use strict';

    angular
        .module('factories.auth', [])
        .factory('Auth', factory);

    /* @ngInject */
    function factory($resource, REGISTER_URL) {
        return $resource(REGISTER_URL, {
        });
    }
})();
