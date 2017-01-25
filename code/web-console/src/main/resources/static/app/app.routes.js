(function() {

    'use strict';

    angular
        .module('app.routes', ['ui.router'])
        .config(routes);

    function routes($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('nav', {
                abstract : true,
                url : '',
                views : {
                    'nav@' : {
                        templateUrl : 'app/sections/navigation/navigation.html',
                        controller : 'NavigationController'
                    }
                }
            })
            .state('login', {
                parent : 'nav',
                url : '/login',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/auth/login/login.html',
                        controller : 'LoginController'
                    }
                }
            })
            .state('home', {
                parent : 'nav',
                url : '/',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/home/home.html',
                        controller : 'HomeController'
                    }
                }
            })
            .state('projects', {
                parent : 'nav',
                url : '/projects',
                data : {
                    role : 'USER'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/project/project.html',
                        controller : 'ProjectController',
                        controllerAs: 'projectCtrl'
                    }
                },
                resolve: {

                    /* @ngInject */
                    projectData: function($q, Categories, Skills) {
                        return $q.all({
                             categories: Categories.getAll(),
                             skills: Skills.getAll()
                        });
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
                        templateUrl : 'app/sections/user/user.html',
                        controller : 'UserController'
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
                        templateUrl : 'app/sections/skill/skill.html',
                        controller : 'SkillController',
                        controllerAs : 'skillCtrl'
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
                        templateUrl : 'app/sections/category/category.html',
                        controller : 'CategoryController'
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
                        templateUrl : 'app/sections/membership/membership.html',
                        controller : 'MembershipController'
                    }
                }
            })
            .state('register', {
                parent : 'nav',
                url : '/register',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/user/register/register.html',
                        controller : 'RegisterController'
                    }
                }
            })
            .state('page-not-found', {
                parent : 'nav',
                url : '/page-not-found',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/page-not-found.html'
                    }
                }
            })
            .state('access-denied', {
                parent : 'nav',
                url : '/access-denied',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/access-denied.html'
                    }
                }
            });
            $urlRouterProvider.otherwise('/page-not-found');
    }
})();