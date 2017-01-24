(function() {
    'use strict';

    angular
        .module('factories.accessTokenStorage', [])
        .factory('AccessTokenStorage', factory);

    /* @ngInject */
    function factory() {
        var storageKey = 'accessToken';
        return {
            store: function(token) {
                return localStorage.setItem(storageKey, token);
            },
            retrieve: function() {
                return localStorage.getItem(storageKey);
            },
            clear: function() {
                return localStorage.removeItem(storageKey);
            }
        };
    }
})();