(function() {
    'use strict';

    angular
        .module('admin', [
            'admin.core',
            'admin.categories',
            'admin.jobs',
            'admin.memberships',
            'admin.skills',
            'admin.users'
        ]);
})();
