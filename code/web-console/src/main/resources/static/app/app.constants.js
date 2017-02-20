(function () {

    return angular.module('app.constants', [])
        .constant('BIDS_URL', 'http://localhost:8888/bids')
        .constant('JOBS_URL', 'http://localhost:8888/jobs')
        .constant('CATEGORIES_URL', 'http://localhost:8888/categories')
        .constant('SKILLS_URL', 'http://localhost:8888/skills')
        .constant('MEMBERSHIPS_URL', 'http://localhost:8888/memberships')
        .constant('USERS_URL', 'http://localhost:8888/users')
        .constant('FEEDBACK_URL', 'http://localhost:8888/users/:userId/feedback')

        .constant('DefaultErrorMessage', 'An error has occurred please contact technical support.');
})();
