(function() {
    'use strict';

    angular
        .module('app.factories', [
            'factories.accessTokenStorage',
            'factories.bids',
            'factories.categories',
            'factories.errorInterceptor',
            'factories.feedback',
            'factories.jobs',
            'factories.refreshTokenStorage',
            'factories.skills',
            'factories.users'
        ]);
})();
