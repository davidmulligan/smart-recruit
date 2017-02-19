(function() {
    'use strict';

    angular
        .module('app.factories', [
            'factories.accessTokenStorage',
            'factories.bids',
            'factories.categories',
            'factories.errorInterceptor',
            'factories.jobs',
            'factories.refreshTokenStorage',
            'factories.skills'
        ]);
})();
