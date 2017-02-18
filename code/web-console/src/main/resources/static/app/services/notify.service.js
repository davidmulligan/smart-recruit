(function() {
    'use strict';

    angular
        .module('services.notifyService', [])
        .service('NotifyService', service);

    /* @ngInject */
    function service($rootScope, $window) {
        return {
            sendMessage: function(message, data) {
                $rootScope.$emit(message, data || {});
            },
            getMessage: function(message, func, scope) {
                var unbind = $rootScope.$on(message, func);
                if (scope) {
                    scope.$on('destroy', unbind);
                }
            }
        }
    }
})();