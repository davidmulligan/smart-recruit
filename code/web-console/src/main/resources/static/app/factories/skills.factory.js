(function() {
    'use strict';

    angular
        .module('factories.skills', [])
        .factory('Skills', factory);

    /* @ngInject */
    function factory($resource, SKILLS_URL) {
        return $resource(SKILLS_URL, { id: '@_id' }, {
            principal: {
                method: 'GET',
                isArray: true,
                url: SKILLS_URL + "/principal"
            }
        });
    }
})();
