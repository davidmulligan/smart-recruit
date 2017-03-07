(function() {
    'use strict';

    angular
        .module('freelancer.account.core', [])
        .controller('FreelancerAccountController', controller);

    /** @ngInject */
    function controller($state) {
        $state.go('freelancer.account.jobs');
    }
})();