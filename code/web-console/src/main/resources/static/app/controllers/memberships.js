angular.module('sr-app')

.controller('MembershipsController', function($http, $scope) {
	var edit = false;
	
	$scope.buttonText = 'Create';
	
	var init = function() {
		$http.get('http://localhost:8888/memberships')
		
		.success(function(result) {
			$scope.memberships = result;
			$scope.membership = null;
			$scope.membershipForm.$setPristine();
			$scope.message = '';
			$scope.buttonText = 'Create';			
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.initEdit = function(membership) {
		edit = true;
		$scope.membership = membership;
		$scope.membershipForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Update';
	};
	
	$scope.initAddMembership = function() {
		edit = false;
		$scope.membership = null;
		$scope.membershipForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Create';
	};
	
	$scope.deleteMembership = function(membership) {
		$http.delete('http://localhost:8888/memberships/' + membership.id)
		
		.success(function(res) {
			$scope.deleteMessage = "Deleted Membership";
			init();
		})
		
		.error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	
	var editMembership = function() {
		$http.put('http://localhost:8888/memberships', $scope.membership)
		
		.success(function(result) {
			$scope.membership = null;
			$scope.membershipForm.$setPristine();
			$scope.message = "Membership Updated";
			init();
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	var addMembership = function() {
        $http.post('http://localhost:8888/memberships', $scope.membership)
		
		.success(function(result) {
			result.membership = null;
			$scope.membershipForm.$setPristine();
			$scope.message = "Membership Created";
			init();
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.submit = function() {
		if (edit){
			editMembership();
		} else {
			addMembership();	
		}
	};
	
	init();

});
