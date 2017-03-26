(function() {
    'use strict';

    angular
        .module('client.account.messages.core', [])
        .controller('ClientAccountMessagesController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
    }
})();