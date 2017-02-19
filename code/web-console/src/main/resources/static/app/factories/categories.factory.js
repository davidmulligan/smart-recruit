(function() {
    'use strict';

    angular
        .module('factories.categories', [])
        .factory('Categories', factory);

    /* @ngInject */
    function factory($resource, CATEGORIES_URL) {
        return $resource(CATEGORIES_URL, { id: '@_id' }, {
            principal: {
                method: 'GET',
                isArray: true,
                url: CATEGORIES_URL + "/principal"
            }
        });
    }
})();
