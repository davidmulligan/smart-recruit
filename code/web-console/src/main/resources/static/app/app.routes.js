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
                        templateUrl : 'app/sections/common/navigation/navigation.html',
                        controller : 'NavigationController',
                        controllerAs : 'ctrl'
                    }
                }
            })

            // Common
            .state('home', {
                parent : 'nav',
                url : '/',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/home/home.html',
                        controller : 'HomeController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state('login', {
                parent : 'nav',
                url : '/login',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/login/login.html',
                        controller : 'LoginController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state('register', {
                parent : 'nav',
                url : '/register',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/register/register.html',
                        controller : 'RegisterController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state('freelancers', {
                parent : 'nav',
                url : '/freelancers',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/freelancers/freelancers.html',
                        controller : 'CommonFreelancersController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state('jobs', {
                parent : 'nav',
                url : '/jobs',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/jobs/jobs.html',
                        controller : 'CommonJobsController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state('help', {
                parent : 'nav',
                url : '/help',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/help/help.html'
                    }
                }
            })
            .state('guide', {
                parent : 'nav',
                url : '/guide',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/help/guide.html'
                    }
                }
            })
            .state('page-not-found', {
                parent : 'nav',
                url : '/page-not-found',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/page-not-found/page-not-found.html'
                    }
                }
            })
            .state('access-denied', {
                parent : 'nav',
                url : '/access-denied',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/access-denied/access-denied.html'
                    }
                }
            })

            // Admin
            .state('admin', {
                parent : 'nav',
                url : '/admin',
                data : {
                    role : 'ADMIN'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/admin/admin.html',
                        controller : 'AdminController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state("admin.jobs", {
                url: "/jobs",
                templateUrl: "app/sections/admin/jobs/jobs.html",
                controller : 'AdminJobsController',
                controllerAs : 'ctrl'
            })
            .state("admin.categories", {
                url: "/categories",
                templateUrl: "app/sections/admin/categories/categories.html",
                controller : 'AdminCategoriesController',
                controllerAs : 'ctrl'
            })
            .state("admin.skills", {
                url: "/skills",
                templateUrl: "app/sections/admin/skills/skills.html",
                controller : 'AdminSkillsController',
                controllerAs : 'ctrl'
            })
            .state("admin.memberships", {
                url: "/memberships",
                templateUrl: "app/sections/admin/memberships/memberships.html",
                controller : 'AdminMembershipsController',
                controllerAs : 'ctrl'
            })
            .state("admin.users", {
                url: "/users",
                templateUrl: "app/sections/admin/users/users.html",
                controller : 'AdminUsersController',
                controllerAs : 'ctrl'
            })

            // Client
            .state('client', {
                parent : 'nav',
                url: "/client",
                abstract: true,
                data : {
                    role : 'CLIENT'
                }
            })
            .state('client.account', {
                parent : 'nav',
                url : '/account',
                data : {
                    role : 'CLIENT'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/client/account/account.html',
                        controller : 'ClientAccountController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state("client.account.jobs", {
                url: "/jobs",
                templateUrl: "app/sections/client/account/jobs/jobs.html",
                controller : 'ClientAccountJobsController',
                controllerAs : 'ctrl'
            })
            .state("client.account.messages", {
                url: "/messages",
                templateUrl: "app/sections/client/account/messages/messages.html",
                controller : 'ClientAccountMessagesController',
                controllerAs : 'ctrl'
            })
            .state("client.account.notifications", {
                url: "/notifications",
                templateUrl: "app/sections/client/account/notifications/notifications.html",
                controller : 'ClientAccountNotificationsController',
                controllerAs : 'ctrl'
            })
            .state('client.post', {
                parent : 'client',
                url : '/post',
                data : {
                    role : 'CLIENT'
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/client/post/post.html',
                        controller : 'ClientPostController',
                        controllerAs : 'ctrl'
                    }
                }
            })

            // Freelancer
            .state('freelancer', {
                parent : 'nav',
                url: "/freelancer",
                abstract: true,
                data : {
                    role : 'FREELANCER'
                }
            })
            .state('freelancer.account', {
                url : '/account',
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/freelancer/account/account.html',
                        controller : 'FreelancerAccountController',
                        controllerAs : 'ctrl'
                    }
                }
            })
            .state("freelancer.account.jobs", {
                url: "/jobs",
                templateUrl: "app/sections/freelancer/account/jobs/jobs.html",
                controller : 'FreelancerAccountJobsController',
                controllerAs : 'ctrl'
            })
            .state("freelancer.account.messages", {
                url: "/messages",
                templateUrl: "app/sections/freelancer/account/messages/messages.html",
                controller : 'FreelancerAccountMessagesController',
                controllerAs : 'ctrl'
            })
            .state("freelancer.account.notifications", {
                url: "/notifications",
                templateUrl: "app/sections/freelancer/account/notifications/notifications.html",
                controller : 'FreelancerAccountNotificationsController',
                controllerAs : 'ctrl'
            })

            $urlRouterProvider.otherwise('/page-not-found');
    }
})();
