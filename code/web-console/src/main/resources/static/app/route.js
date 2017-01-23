angular.module('sr-app').config(function($stateProvider, $urlRouterProvider) {

	$stateProvider

	.state('nav', {
		abstract : true,
		url : '',
		views : {
			'nav@' : {
				templateUrl : 'app/views/navigation.html',
				controller : 'NavigationController'
			}
		}
	})

	.state('login', {
		parent : 'nav',
		url : '/login',
		views : {
			'content@' : {
				templateUrl : 'app/views/login.html',
				controller : 'LoginController'
			}
		}
	})

	.state('home', {
        parent : 'nav',
        url : '/',
        views : {
            'content@' : {
                templateUrl : 'app/views/home.html',
                controller : 'HomeController'
            }
        }
    })

	.state('users', {
		parent : 'nav',
		url : '/users',
		data : {
			role : 'USER'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/users.html',
				controller : 'UsersController'
			}
		}
	})

	.state('skills', {
        parent : 'nav',
        url : '/skills',
        data : {
            role : 'USER'
        },
        views : {
            'content@' : {
                templateUrl : 'app/views/skills.html',
                controller : 'SkillsController'
            }
        }
    })

    .state('categories', {
        parent : 'nav',
        url : '/categories',
        data : {
            role : 'USER'
        },
        views : {
            'content@' : {
                templateUrl : 'app/views/categories.html',
                controller : 'CategoriesController'
            }
        }
    })

    .state('memberships', {
        parent : 'nav',
        url : '/memberships',
        data : {
            role : 'USER'
        },
        views : {
            'content@' : {
                templateUrl : 'app/views/memberships.html',
                controller : 'MembershipsController'
            }
        }
    })

    .state('register', {
		parent : 'nav',
		url : '/register',
		views : {
			'content@' : {
				templateUrl : 'app/views/register.html',
				controller : 'RegisterController'
			}
		}
	})

	.state('page-not-found', {
		parent : 'nav',
		url : '/page-not-found',
		views : {
			'content@' : {
				templateUrl : 'app/views/page-not-found.html'
			}
		}
	})

	.state('access-denied', {
		parent : 'nav',
		url : '/access-denied',
		views : {
			'content@' : {
				templateUrl : 'app/views/access-denied.html'
			}
		}
	});

	$urlRouterProvider.otherwise('/page-not-found');
});
