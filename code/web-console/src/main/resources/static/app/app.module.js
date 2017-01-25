(function() {
    'use strict';

    angular
        .module('app.module', [
            'app.config',
            'auth',
            'navigation',
            'home',
            'category',
            'skill',
            'membership',
            'user',
            'project'
    ]);
})();
