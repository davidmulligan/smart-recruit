(function() {
    'use strict';

    angular
        .module('client.account.core', [])
        .controller('ClientAccountController', controller);

    /** @ngInject */
    function controller($http, $state, ngToast) {
        $state.go('account.jobs');
    }
})();