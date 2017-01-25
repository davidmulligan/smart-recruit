(function() {
    'use strict';

    angular
        .module('factories.categories', [])
        .factory('Categories', factory);

    /* @ngInject */
    function factory($resource, CATEGORIES_URL) {
        var mt = $resource(CATEGORIES_URL);
        return {
            getAll: function() {
                return mt.getAll();
            }
        };
    }
})();
