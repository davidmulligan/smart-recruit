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
                data : {
                    secure : false
                },
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
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/login/login.html',
                        controller : 'LoginController',
                        controllerAs : 'ctrl'
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
                        templateUrl : 'app/sections/common/freelancers/freelancers.html',
                        controller : 'CommonFreelancersController',
                        controllerAs : 'ctrl'
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
            .state('jobs', {
                parent : 'nav',
                url : '/jobs',
                data : {
                    secure : false
                },
                views : {
                    'content@' : {
                        templateUrl : 'app/sections/common/jobs/jobs.html',
                        controller : 'CommonJobsController',
                        controllerAs : 'ctrl'
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
                        templateUrl : 'app/sections/common/register/register.html',
                        controller : 'RegisterController',
                        controllerAs : 'ctrl'
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
                        templateUrl : 'app/sections/common/help/help.html'
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
                        templateUrl : 'app/sections/common/help/guide.html'
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
                        templateUrl : 'app/sections/common/page-not-found/page-not-found.html'
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
                templateUrl: "app/sections/admin/job/job.html",
                controller : 'AdminJobController',
                controllerAs : 'ctrl'
            })
            .state("admin.categories", {
                url: "/categories",
                templateUrl: "app/sections/admin/category/category.html",
                controller : 'AdminCategoryController',
                controllerAs : 'ctrl'
            })
            .state("admin.skills", {
                url: "/skills",
                templateUrl: "app/sections/admin/skill/skill.html",
                controller : 'AdminSkillController',
                controllerAs : 'ctrl'
            })
            .state("admin.memberships", {
                url: "/memberships",
                templateUrl: "app/sections/admin/membership/membership.html",
                controller : 'AdminMembershipController',
                controllerAs : 'ctrl'
            })
            .state("admin.users", {
                url: "/users",
                templateUrl: "app/sections/admin/user/user.html",
                controller : 'AdminUserController',
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
            .state("client.account.disputeManagement", {
                url: "/disputeManagement",
                templateUrl: "app/sections/client/account/dispute/dispute.html",
                controller : 'ClientAccountDisputeController',
                controllerAs : 'ctrl'
            })
            .state("client.account.jobs", {
                url: "/jobs",
                templateUrl: "app/sections/client/account/job/job.html",
                controller : 'ClientAccountJobController',
                controllerAs : 'ctrl'
            })
            .state("client.account.messages", {
                url: "/messages",
                templateUrl: "app/sections/client/account/message/message.html",
                controller : 'ClientAccountMessageController',
                controllerAs : 'ctrl'
            })
            .state("client.account.notifications", {
                url: "/notifications",
                templateUrl: "app/sections/client/account/notification/notification.html",
                controller : 'ClientAccountNotificationController',
                controllerAs : 'ctrl'
            })
            .state('client.post', {
                parent : 'nav',
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
            .state("freelancer.account.bids", {
                url: "/bids",
                templateUrl: "app/sections/freelancer/account/bids/bids.html",
                controller : 'FreelancerAccountBidsController',
                controllerAs : 'ctrl'
            })
            .state("freelancer.account.disputeManagement", {
                url: "/disputeManagement",
                templateUrl: "app/sections/freelancer/account/dispute_management/dispute_management.html",
                controller : 'FreelancerAccountDisputeManagementController',
                controllerAs : 'ctrl'
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
