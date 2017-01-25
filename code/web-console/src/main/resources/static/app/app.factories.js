(function() {
    'use strict';

    angular
        .module('app.factories', [
            'factories.accessTokenStorage',
            'factories.refreshTokenStorage',
            'factories.categories',
            'factories.skills'
        ]);
})();
