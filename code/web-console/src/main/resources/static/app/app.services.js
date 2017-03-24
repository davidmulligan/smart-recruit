(function() {
    'use strict';

    angular
        .module('app.services', [
            'services.authenticationService',
            'services.jwtService',
            'services.modalService',
            'services.notifyService'
        ]);
})();
