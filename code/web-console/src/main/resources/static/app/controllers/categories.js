angular.module('sr-app')

.controller('CategoriesController', function($http, $scope) {
	var edit = false;
	
	$scope.buttonText = 'Create';
	
	var init = function() {
		$http.get('http://localhost:8888/categories')
		
		.success(function(result) {
			$scope.categories = result;
			$scope.category = null;
			$scope.categoryForm.$setPristine();
			$scope.message = '';
			$scope.buttonText = 'Create';			
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.initEdit = function(category) {
		edit = true;
		$scope.category = category;
		$scope.categoryForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Update';
	};
	
	$scope.initAddCategory = function() {
		edit = false;
		$scope.category = null;
		$scope.categoryForm.$setPristine();
		$scope.message = '';
		$scope.buttonText = 'Create';
	};
	
	$scope.deleteCategory = function(category) {
		$http.delete('http://localhost:8888/categories/' + category.id)
		
		.success(function(res) {
			$scope.deleteMessage = "Deleted Category";
			init();
		})
		
		.error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	
	var editCategory = function() {
		$http.put('http://localhost:8888/categories', $scope.category)
		
		.success(function(result) {
			$scope.category = null;
			$scope.categoryForm.$setPristine();
			$scope.message = "Category Updated";
			init();
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	var addCategory = function() {
        $http.post('http://localhost:8888/categories', $scope.category)
		
		.success(function(result) {
			result.category = null;
			$scope.categoryForm.$setPristine();
			$scope.message = "Category Created";
			init();
		})
		
		.error(function(error) {
			$scope.message = error.message;
		});
	};
	
	$scope.submit = function() {
		if (edit){
			editCategory();
		} else {
			addCategory();
		}
	};
	
	init();

});
