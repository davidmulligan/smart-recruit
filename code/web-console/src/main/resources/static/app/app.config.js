(function() {
    'use strict';

    angular
        .module('app.config', [
        //'app.constants',
        //'app.factories',
        //'app.services',
        //'app.directives',
        'app.module'
    ])
    .run(runner)
    .config(state)
    .config(config);

    /* @ngInject */
    function runner($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }

    /* @ngInject */
    function state($stateProvider) {
        $stateProvider.linkState = angular.linkState;
    }

    /** @ngInject */
    function config($logProvider, $httpProvider) {

        // Enable log
        $logProvider.debugEnabled(true);
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.post = {};
        $httpProvider.defaults.headers.put = {};
        $httpProvider.defaults.headers.patch = {};
    }

})();
