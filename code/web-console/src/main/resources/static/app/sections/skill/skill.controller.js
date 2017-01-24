(function() {
    'use strict';

    angular
        .module('skill.core', [])
        .controller('SkillController', controller);

    /** @ngInject */
    function controller($http, $scope) {

        var edit = false;

        $scope.buttonText = 'Create';

        var init = function() {
            $http.get('http://localhost:8888/skills')

            .success(function(result) {
                $scope.skills = result;
                $scope.skill = null;
                $scope.skillForm.$setPristine();
                $scope.message = '';
                $scope.buttonText = 'Create';
            })

            .error(function(error) {
                $scope.message = error.message;
            });
        };

        $scope.initEdit = function(skill) {
            edit = true;
            $scope.skill = skill;
            $scope.skillForm.$setPristine();
            $scope.message = '';
            $scope.buttonText = 'Update';
        };

        $scope.initAddSkill = function() {
            edit = false;
            $scope.skill = null;
            $scope.skillForm.$setPristine();
            $scope.message = '';
            $scope.buttonText = 'Create';
        };

        $scope.deleteSkill = function(skill) {
            $http.delete('http://localhost:8888/skills/' + skill.id)

            .success(function(res) {
                $scope.deleteMessage = "Deleted Skill";
                init();
            })

            .error(function(error) {
                $scope.deleteMessage = error.message;
            });
        };

        var editSkill = function() {
            $http.put('http://localhost:8888/skills', $scope.skill)

            .success(function(result) {
                $scope.skill = null;
                $scope.skillForm.$setPristine();
                $scope.message = "Skill Updated";
                init();
            })

            .error(function(error) {
                $scope.message = error.message;
            });
        };

        var addSkill = function() {
            $http.post('http://localhost:8888/skills', $scope.skill)

            .success(function(result) {
                result.skill = null;
                $scope.skillForm.$setPristine();
                $scope.message = "Skill Created";
                init();
            })

            .error(function(error) {
                $scope.message = error.message;
            });
        };

        $scope.submit = function() {
            if (edit){
                editSkill();
            } else {
                addSkill();
            }
        };

        init();
    }
})();