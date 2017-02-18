(function() {
    'use strict';

    angular
        .module('client.account', [
            'client.account.core',
            'client.account.dispute',
            'client.account.job',
            'client.account.message',
            'client.account.notification'
        ]);
})();
