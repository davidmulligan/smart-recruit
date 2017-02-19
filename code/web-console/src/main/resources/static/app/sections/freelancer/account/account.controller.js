(function() {
    'use strict';

    angular
        .module('freelancer.account.core', [])
        .controller('FreelancerAccountController', controller);

    /** @ngInject */
    function controller($http, $state, ngToast) {
        $state.go('freelancer.account.jobs');
    }
})();