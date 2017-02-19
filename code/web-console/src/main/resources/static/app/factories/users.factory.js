(function() {
    'use strict';

    angular
        .module('factories.users', [])
        .factory('Users', factory);

    /* @ngInject */
    function factory($resource, USERS_URL) {
        return $resource(USERS_URL, { id: '@_id' }, {
            freelancers: {
                method: 'GET',
                isArray: true,
                url: USERS_URL + "/freelancers?firstNameOrLastName=:query",
                params: {query: '@query'}
            }
        });
    }
})();
