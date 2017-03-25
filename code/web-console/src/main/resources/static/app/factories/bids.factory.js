(function() {
    'use strict';

    angular
        .module('factories.bids', [])
        .factory('Bids', factory);

    /* @ngInject */
    function factory($resource, BIDS_URL) {
        return $resource(BIDS_URL, { id: '@_id' }, {
            self: {
                method: 'GET',
                url: BIDS_URL + "/self",
                isArray: true
            }
        });
    }
})();
