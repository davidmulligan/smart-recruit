(function() {
    'use strict';

    angular
        .module('freelancer.account.bids.core', [])
        .controller('FreelancerAccountBidsController', controller);

    /** @ngInject */
    function controller(Bids, $log) {
        var vm = this;

        vm.init = function() {
            Bids.self(
                function(data) {
                   vm.bids = data;
                   $log.info('Successfully fetched ' + data.length + ' bids.');
                }
            );
        };

        vm.init();
    }
})();