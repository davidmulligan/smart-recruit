angular.module('sr-app')

.controller('LoginController', function($scope, $state, AuthenticationService) {

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

});
