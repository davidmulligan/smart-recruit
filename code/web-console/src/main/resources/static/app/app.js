angular.module('sr-app', [ 'ui.router', 'sr-app.factories' ])

// The following method will run at the time of initializing the module, running once.
.run(function($rootScope, $state, AuthenticationService) {

    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		if (!AuthenticationService.isAuthenticated()) {
			if (toState.name != 'login' && toState.name != 'register') {
                event.preventDefault();
				$state.go('login');
			}
		} else {
			if (toState.data && toState.data.role) {
                var hasAccess = true;
//				for (var i = 0; i < AuthenticationService.getCurrentUser().roles.length; i++) {
//					var role = AuthenticationService.user.roles[i];
//					if (toState.data.role == role) {
//						hasAccess = true;
//						break;
//					}
//				}
				if (!hasAccess) {
				    event.preventDefault();
					$state.go('access-denied');
				}
			}
		}
	});

});