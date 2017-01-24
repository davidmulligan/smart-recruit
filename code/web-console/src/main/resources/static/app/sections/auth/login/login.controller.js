(function() {
    'use strict';

    angular
        .module('login.core', [])
        .controller('LoginController', controller);

    /** @ngInject */
    function controller($scope, $state, AuthenticationService) {

        $scope.login = function() {
            AuthenticationService.login({username: $scope.username, password: $scope.password})
                .then(function(result) {
                    $scope.password = null;
                    if (AuthenticationService.isAuthenticated()) {
                        $scope.message = '';
                        $state.go('home');
                    } else {
                        $scope.message = 'Authentication Failed';
                    }
                })
                .catch(function() {
                    $scope.message = 'Authentication Failed';
                });
    	};
    }
})();