angular.module('sr-app')

.controller('NavigationController', function($http, $scope, AuthenticationService, $state, $rootScope) {

	$scope.$on('LoginSuccessful', function() {
        $scope.user = AuthenticationService.getCurrentUser();
	});

	$scope.$on('LogoutSuccessful', function() {
		AuthenticationService.clearCurrentUser();
		$scope.user = null;
	});

	$scope.logout = function() {
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};

});
