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
                    jobData: function($q, Categories, Skills) {
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

            .state('jobs', {
                parent : 'nav',
                url : '/jobs',
                data : {
                    role : 'USER',
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/jobs/jobs.html',
                        controller : 'JobsController',
                        controllerAs : 'jobsCtrl'
                    }
                }
            })

            .state('myjobs', {
                parent : 'nav',
                url : '/myjobs',
                data : {
                    role : 'USER'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/jobs/myjobs/myjobs.html',
                        controller : 'MyJobsController',
                        controllerAs : 'myJobsCtrl'
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
            .state('job', {
                parent : 'nav',
                url : '/job/:selectedJob',
                data : {
                    role : 'USER'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/job/viewjob/viewjob.html',
                        controller : 'ViewJobController',
                        controllerAs: 'viewJobCtrl'
                    }
                }
            })


            .state('postJob', {
                parent : 'nav',
                url : '/postJob',
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
