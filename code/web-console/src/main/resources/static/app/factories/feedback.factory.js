(function() {
    'use strict';

    angular
        .module('factories.feedback', [])
        .factory('Feedback', factory);

    /* @ngInject */
    function factory($resource, FEEDBACK_URL) {
        return $resource(FEEDBACK_URL, { userId: '@userId' }, {
        });
    }
})();
