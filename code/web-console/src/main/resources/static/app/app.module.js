(function() {
    'use strict';

    angular
        .module('app.module', [
            'app.config',
            'navigation',
            'home',
            'login',
            'admin',
            'client',
            'freelancer'
    ]);
})();
