(function() {
    'use strict';

    angular
        .module('factories.errorInterceptor', [])
        .factory('ErrorInterceptor', factory);

     /* @ngInject */
    function factory($q, $injector, ngToast, DefaultErrorMessage) {
        return {
            responseError: function(error) {
                if (error.status === 401) {
                    $injector.get('$state').transitionTo("login");
                } else if (error.status === 400 && error.data) {
                    ngToast.danger(error.data.message);
                } else {
                    ngToast.danger(DefaultErrorMessage);
                }
                return $q.reject(error);
            }
        };
    }
})();