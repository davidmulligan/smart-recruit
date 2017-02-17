(function() {
    'use strict';

    angular
        .module('admin', [
            'admin.core',
            'admin.category',
            'admin.job',
            'admin.membership',
            'admin.skill',
            'admin.user'
        ]);
})();
