(function() {
    'use strict';

    angular
        .module('services.authenticationService', [])
        .service('AuthenticationService', service);

    /* @ngInject */
    function service($rootScope, $http, AccessTokenStorage, RefreshTokenStorage, JwtService) {
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
            },
            isAuthenticated: function() {
                return (currentUser) ? currentUser : false;
            },
            login: function(credentials) {
                return $http.post('http://localhost:8888/auth/login', credentials)
                    .then(function(result) {
                        if (result.status == 200) {
                            AccessTokenStorage.store(result.data.token);
                            RefreshTokenStorage.store(result.data.refreshToken);
                            currentUser = JwtService.getUser(AccessTokenStorage.retrieve());
                            $http.defaults.headers.common['X-Authorization'] = 'Bearer ' + AccessTokenStorage.retrieve();
                            $rootScope.$broadcast('LoginSuccessful');
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
            }
        }
    }
})();