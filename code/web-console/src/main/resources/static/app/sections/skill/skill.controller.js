(function() {
    'use strict';

    angular
        .module('skill.core', [])
        .controller('SkillController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get('http://localhost:8888/skills')

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
            $http.delete('http://localhost:8888/skills/' + skill.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Skill";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editSkill = function() {
            $http.put('http://localhost:8888/skills', vm.skill)

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
            $http.post('http://localhost:8888/skills', vm.skill)

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