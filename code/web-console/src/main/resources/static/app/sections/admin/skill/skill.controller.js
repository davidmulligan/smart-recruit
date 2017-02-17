(function() {
    'use strict';

    angular
        .module('admin.skill.core', [])
        .controller('AdminSkillController', controller);

    /** @ngInject */
    function controller($http, ngToast, SKILLS_URL) {
        var vm = this;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(SKILLS_URL)

            .success(function(result) {
                vm.skills = result;
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.initEdit = function(skill) {
            vm.edit = true;
            vm.skill = skill;
            vm.skillForm.$setPristine();
            vm.buttonText = 'Update';
        };

        vm.initCreate = function() {
            vm.edit = false;
            vm.skill = null;
            vm.skillForm.$setPristine();
            vm.buttonText = 'Create';
        };

        vm.deleteSkill = function(skill) {
            $http.delete(SKILLS_URL + '/' + skill.id)

            .success(function(result) {
                ngToast.success("Deleted skill: " + skill.name);
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.editSkill = function() {
            $http.put(SKILLS_URL, vm.skill)

            .success(function(result) {
                ngToast.success("Updated skill: " + vm.skill.name);
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.addSkill = function() {
            $http.post(SKILLS_URL, vm.skill)

            .success(function(result) {
                ngToast.success("Created skill: " + vm.skill.name);
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.init();
            })

            .error(function(error) {
                ngToast.danger(error.message);
            });
        };

        vm.submit = function() {
            if (vm.edit){
                vm.editSkill();
            } else {
                vm.addSkill();
            }
        };

        vm.init();
    }
})();