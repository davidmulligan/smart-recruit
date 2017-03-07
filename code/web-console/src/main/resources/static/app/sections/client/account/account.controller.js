(function() {
    'use strict';

    angular
        .module('client.account.core', [])
        .controller('ClientAccountController', controller);

    /** @ngInject */
    function controller($state) {
        $state.go('client.account.jobs');
    }
})();