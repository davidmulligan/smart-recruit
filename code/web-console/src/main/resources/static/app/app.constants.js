(function () {

    return angular.module('app.constants', [])
        .constant('JOBS_URL', 'http://localhost:8888/projects')
        .constant('CATEGORIES_URL', 'http://localhost:8888/categories')
        .constant('SKILLS_URL', 'http://localhost:8888/skills');
})();
