(function() {
    'use strict';

    angular
        .module('factories.jobs', [])
        .factory('Jobs', factory);

    /* @ngInject */
    function factory($resource, JOBS_URL) {
        return $resource(JOBS_URL, { id: '@_id' }, {
            self: {
                method: 'GET',
                url: JOBS_URL + "/self",
                isArray: true
            },
            cancel: {
                method: 'PUT',
                url: JOBS_URL + '/:id/cancel',
                params: {id: '@id'}
            },
            approve: {
                method: 'PUT',
                url: JOBS_URL + '/:id/approve',
                params: {id: '@id'}
            },
            reject: {
                method: 'PUT',
                url: JOBS_URL + '/:id/reject',
                params: {id: '@id'}
            }
        });
    }
})();
