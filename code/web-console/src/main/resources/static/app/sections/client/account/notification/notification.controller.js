(function() {
    'use strict';

    angular
        .module('client.account.notification.core', [])
        .controller('ClientAccountNotificationController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
    }
})();