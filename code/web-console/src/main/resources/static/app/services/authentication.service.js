(function() {
    'use strict';

    angular
        .module('services.authenticationService', [])
        .service('AuthenticationService', service);

    /* @ngInject */
    function service($http, LOGIN_URL, AccessTokenStorage, RefreshTokenStorage, JwtService, NotifyService) {
        var currentUser;
        return {
            setCurrentUser: function(user) {
                currentUser = user;
            },
            getCurrentUser: function() {
                return currentUser;
            },
            clearCurrentUser: function() {
                currentUser = null;
                AccessTokenStorage.clear();
                RefreshTokenStorage.clear();
                $http.defaults.headers.common['X-Authorization'] = null;
            },
            isAuthenticated: function() {
                return (currentUser) ? currentUser : false;
            },
            login: function(credentials) {
                return $http.post(LOGIN_URL, credentials)
                    .then(function(result) {
                        if (result.status == 200) {
                            AccessTokenStorage.store(result.data.token);
                            RefreshTokenStorage.store(result.data.refreshToken);
                            currentUser = JwtService.getUser(AccessTokenStorage.retrieve());
                            $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + AccessTokenStorage.retrieve();
                            NotifyService.sendMessage('LoginEvent', currentUser);
                        }
                    })
                    .catch(function(e) {
                        AccessTokenStorage.clear();
                        RefreshTokenStorage.clear();
                        throw e;
                    });
            },
            recoverToken: function() {
                currentUser = JwtService.getUser(AccessTokenStorage.retrieve());
                $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + AccessTokenStorage.retrieve();
                NotifyService.sendMessage('LoginEvent', currentUser);
            }
        }
    }
})();