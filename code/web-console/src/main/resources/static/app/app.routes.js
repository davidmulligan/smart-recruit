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
                data : {
                    secure : true
                },
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
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/login/login.html',
                        controller : 'LoginController',
                        controllerAs : 'loginCtrl'
                    }
                }
            })
            .state('register', {
                parent : 'nav',
                url : '/register',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/user/register/register.html',
                        controller : 'RegisterController',
                        controllerAs : 'registerCtrl'
                    }
                }
            })
            .state('home', {
                parent : 'nav',
                url : '/',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/home/home.html',
                        controller : 'HomeController',
                        controllerAs : 'homeCtrl'
                    }
                }
            })
            .state('freelancers', {
                parent : 'nav',
                url : '/freelancers',
                data : {
                    role : 'USER',
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/freelancers/freelancers.html',
                        controller : 'FreelancersController',
                        controllerAs : 'freelancersCtrl'
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
            .state('project', {
                parent : 'nav',
                url : '/project/:selectedProject',
                data : {
                    role : 'USER'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/project/viewproject/viewproject.html',
                        controller : 'ViewProjectController',
                        controllerAs: 'viewProjectCtrl'
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
                        controller : 'UserController',
                        controllerAs : 'userCtrl'
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
                        controller : 'CategoryController',
                        controllerAs : 'categoryCtrl'
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
                        controller : 'MembershipController',
                        controllerAs : 'membershipCtrl'
                    }
                }
            })


            .state('page-not-found', {
                parent : 'nav',
                url : '/page-not-found',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/page-not-found.html'
                    }
                }
            })
            .state('access-denied', {
                parent : 'nav',
                url : '/access-denied',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/access-denied.html'
                    }
                }
            });

            $urlRouterProvider.otherwise('/page-not-found');
    }
})();
