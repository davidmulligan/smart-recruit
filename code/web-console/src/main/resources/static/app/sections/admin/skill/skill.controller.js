(function() {
    'use strict';

    angular
        .module('skill.core', [])
        .controller('SkillController', controller);

    /** @ngInject */
    function controller($http, SKILLS_URL) {
        var vm = this;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get(SKILLS_URL)

            .success(function(result) {
                vm.skills = result;
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.message = '';
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.initEdit = function(skill) {
            vm.edit = true;
            vm.skill = skill;
            vm.skillForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Update';
        };

        vm.initAddSkill = function() {
            vm.edit = false;
            vm.skill = null;
            vm.skillForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Create';
        };

        vm.deleteSkill = function(skill) {
            $http.delete(SKILLS_URL + '/' + skill.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Skill";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editSkill = function() {
            $http.put(SKILLS_URL, vm.skill)

            .success(function(result) {
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.message = "Skill Updated";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.addSkill = function() {
            $http.post(SKILLS_URL, vm.skill)

            .success(function(result) {
                vm.skill = null;
                vm.skillForm.$setPristine();
                vm.message = "Skill Created";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
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