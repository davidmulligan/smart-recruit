angular.module('sr-app')

.controller('HomeController', function($scope, AuthenticationService) {

	$scope.user = AuthenticationService.getCurrentUser();

});
