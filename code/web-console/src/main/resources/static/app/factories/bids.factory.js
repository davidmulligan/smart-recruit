(function() {
    'use strict';

    angular
        .module('factories.bids', [])
        .factory('Bids', factory);

    /* @ngInject */
    function factory($resource, BIDS_URL) {
        return $resource(BIDS_URL, { id: '@_id' }, {
            post: {
                method: 'POST',
                url: BIDS_URL,
                params: {jobId: '@jobId'}
            },
            self: {
                method: 'GET',
                url: BIDS_URL + "/self",
                isArray: true
            }
        });
    }
})();
