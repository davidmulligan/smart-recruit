angular.module('sr-app')

.controller('UsersController', function($http, $scope) {

	var edit = false;
	
	$scope.buttonText = 'Create';
	
	var init = function() {
		$http.get('http://localhost:8888/users')
		
		.success(function(result) {
			$scope.users = result;
			$scope.user = null;		
			$scope.userForm.$setPristine();
			$scope.message = '';
			$scope.buttonText = 'Create';
			
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.initEdit = function(user) {
		edit = true;
		$scope.user = user;
		$scope.userForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Update';
	};
	
	$scope.initAddUser = function() {
		edit = false;
		$scope.user = null;
		$scope.userForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Create';
	};
	
	$scope.deleteUser = function(user) {
		$http.delete('http://localhost:8888/users/' + user.id)

		.success(function(result) {
			$scope.deleteMessage = "Deleted User";
			init();
		})

		.error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};

	var editUser = function(){
		$http.put('http://localhost:8888/users', $scope.user)

		.success(function(result) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.message = "User Updated";
			init();
		})

		.error(function(error) {
			$scope.message = error.message;
		});
	};

	var addUser = function(){
		$http.post('http://localhost:8888/users', $scope.user)

		.success(function(result) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.message = "User Created";
			init();
		})

		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.submit = function() {
		if (edit) {
			editUser();
		} else{
			addUser();	
		}
	};
	
	init();

});
