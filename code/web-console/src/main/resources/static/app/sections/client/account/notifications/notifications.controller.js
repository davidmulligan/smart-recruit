(function() {
    'use strict';

    angular
        .module('client.account.notifications.core', [])
        .controller('ClientAccountNotificationsController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
    }
})();