(function () {

    return angular.module('app.constants', [])
        .constant('CATEGORIES_URL', 'http://localhost:8888/categories')
        .constant('SKILLS_URL', 'http://localhost:8888/skills');
})();