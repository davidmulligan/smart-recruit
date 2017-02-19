(function() {
    'use strict';

    angular
        .module('freelancer.account', [
            'freelancer.account.core',
            'freelancer.account.bids',
            'freelancer.account.dispute_management',
            'freelancer.account.jobs',
            'freelancer.account.messages',
            'freelancer.account.notifications'
        ]);
})();
