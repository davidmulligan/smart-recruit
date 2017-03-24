(function () {

    return angular.module('app.constants', [])
        .constant('LOGIN_URL', 'http://localhost:8888/auth/login')
        .constant('REGISTER_URL', 'http://localhost:8888/auth/register')
        .constant('BIDS_URL', 'http://localhost:8888/bids')
        .constant('CATEGORIES_URL', 'http://localhost:8888/categories')
        .constant('FEEDBACK_URL', 'http://localhost:8888/users/:userId/feedback')
        .constant('JOBS_URL', 'http://localhost:8888/jobs')
        .constant('MEMBERSHIPS_URL', 'http://localhost:8888/memberships')
        .constant('SKILLS_URL', 'http://localhost:8888/skills')
        .constant('USERS_URL', 'http://localhost:8888/users')

        .constant('DefaultErrorMessage', 'An error has occurred please contact technical support.');
})();
