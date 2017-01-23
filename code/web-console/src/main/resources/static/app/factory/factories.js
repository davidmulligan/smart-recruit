'use strict';

var app = angular.module('sr-app.factories', [])

app.factory('AccessTokenStorage', function() {
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
});

app.factory('RefreshTokenStorage', function() {
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
});