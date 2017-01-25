(function() {
    'use strict';

    angular
        .module('factories.skills', [])
        .factory('Skills', factory);

    /* @ngInject */
    function factory($resource, SKILLS_URL) {
        var mt = $resource(SKILLS_URL);
        return {
            getAll: function() {
                return mt.getAll();
            }
        };
    }
})();
