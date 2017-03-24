(function() {
    'use strict';

   /**
    * Extension method linking a child state given an array of states representing parent > child
    * $stateProvider.linkState(grandParent, parent, child);
    */
    angular.linkState = function() {
        var states = Array.prototype.slice.call(arguments);

        var stateName = states.map(function(iState) {
            return iState.name;
        }).join('.');

        var state = angular.copy(states.pop());
        return this.state(stateName, state);
    };
})();
