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
                },
                resolve: {

                    /* @ngInject */
                    referenceData: function($q, Categories, Skills) {
                        return $q.all({
                            categories: Categories.getAll(),
                            skills: Skills.getAll()
                        });
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
            .state('help', {
                parent : 'nav',
                url : '/help',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/help.html'
                    }
                }
            })
            .state('guide', {
                parent : 'nav',
                url : '/guide',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/guide.html'
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
            })

            // Client
            .state('account', {
                parent : 'nav',
                url : '/account',
                data : {
                    role : 'CLIENT'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/client/account/account.html',
                        controller : 'ClientAccountController',
                        controllerAs : 'accountCtrl'
                    }
                }
            })
            .state('post', {
                parent : 'nav',
                url : '/post',
                data : {
                    role : 'CLIENT'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/client/job/job.html',
                        controller : 'ClientJobController',
                        controllerAs : 'jobCtrl'
                    }
                }
            })





            .state('freelancers', {
                parent : 'nav',
                url : '/freelancers',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/client/freelancer/freelancer.html',
                        controller : 'ClientFreelancerController',
                        controllerAs : 'freelancersCtrl'
                    }
                }
            })
            .state('jobs', {
                parent : 'nav',
                url : '/jobs',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/freelancer/job/job.html',
                        controller : 'FreelancerJobController',
                        controllerAs : 'jobCtrl'
                    }
                }
            })


            .state('categories', {
                parent : 'nav',
                url : '/categories',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/category/category.html',
                        controller : 'AdminCategoryController',
                        controllerAs : 'categoryCtrl'
                    }
                }
            })
            .state('adminJobs', {
                parent : 'nav',
                url : '/adminJobs',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/job/job.html',
                        controller : 'AdminJobController',
                        controllerAs : 'adminJobCtrl'
                    }
                }
            })
            .state('memberships', {
                parent : 'nav',
                url : '/memberships',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/membership/membership.html',
                        controller : 'AdminMembershipController',
                        controllerAs : 'membershipCtrl'
                    }
                }
            })
            .state('skills', {
                parent : 'nav',
                url : '/skills',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/skill/skill.html',
                        controller : 'AdminSkillController',
                        controllerAs : 'skillCtrl'
                    }
                }
            })
            .state('users', {
                parent : 'nav',
                url : '/users',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/user/user.html',
                        controller : 'AdminUserController',
                        controllerAs : 'userCtrl'
                    }
                }
            });



            $urlRouterProvider.otherwise('/page-not-found');
    }
})();
