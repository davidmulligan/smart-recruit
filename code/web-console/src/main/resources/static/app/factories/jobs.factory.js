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
            placeBid: {
                method: 'POST',
                url: JOBS_URL + '/:id/bids',
                params: {id: '@id'}
            },
            cancel: {
                method: 'PUT',
                url: JOBS_URL + '/:id/cancel',
                params: {id: '@id'}
            },
            accept: {
                method: 'PUT',
                url: JOBS_URL + '/:id/bids/:bidId/accept',
                params: {id: '@id', bidId: '@bidId'}
            }
        });
    }
})();
