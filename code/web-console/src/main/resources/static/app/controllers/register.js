angular.module('sr-app')

.controller('RegisterController', function($http, $scope, AuthenticationService) {

	$scope.submit = function() {
		$http.post('http://localhost:8888/auth/register', $scope.user)
		
		.success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.register.$setPristine();
			$scope.message = "Registration Successful";
		})

		.error(function(error) {
			$scope.message = error.message;
		});

	};
});
