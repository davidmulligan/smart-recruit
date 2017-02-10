(function () {

    return angular.module('app.constants', [])
        .constant('JOBS_URL', 'http://localhost:8888/jobs')
        .constant('CATEGORIES_URL', 'http://localhost:8888/categories')
        .constant('SKILLS_URL', 'http://localhost:8888/skills')
        .constant('MEMBERSHIPS_URL', 'http://localhost:8888/memberships')
        .constant('USERS_URL', 'http://localhost:8888/users');
})();
