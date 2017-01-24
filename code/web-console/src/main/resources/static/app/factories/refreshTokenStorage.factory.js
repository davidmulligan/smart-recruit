(function() {
    'use strict';

    angular
        .module('factories.refreshTokenStorage', [])
        .factory('RefreshTokenStorage', factory);

    /* @ngInject */
    function factory() {
        var storageKey = 'refreshToken';
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