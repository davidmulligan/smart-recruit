(function() {
    'use strict';

    angular
        .module('client.account', [
            'client.account.core',
            'client.account.jobs',
            'client.account.messages',
            'client.account.notifications'
        ]);
})();
