(function() {
    'use strict';

    angular
        .module('project.core', [])
        .controller('ProjectController', controller);

    /** @ngInject */
    function controller($http, projectData) {
        var vm = this;
        vm.categories = projectData.categories;
        vm.skills = projectData.skills;
        vm.edit = false;
        vm.buttonText = 'Create';

        vm.init = function() {
            $http.get('http://localhost:8888/projects')

            .success(function(result) {
                vm.projects = result;
                vm.project = null;
                vm.projectForm.$setPristine();
                vm.message = '';
                vm.buttonText = 'Create';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.initEdit = function(project) {
            vm.edit = true;
            vm.project = project;
            vm.projectForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Update';
        };

        vm.initAddProject = function() {
            vm.edit = false;
            vm.project = null;
            vm.projectForm.$setPristine();
            vm.message = '';
            vm.buttonText = 'Create';
        };

        vm.deleteProject = function(project) {
            $http.delete('http://localhost:8888/projects/' + project.id)

            .success(function(res) {
                vm.deleteMessage = "Deleted Project";
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.editProject = function() {
            $http.put('http://localhost:8888/projects', vm.project)

            .success(function(result) {
                vm.project = null;
                vm.projectForm.$setPristine();
                vm.message = "Project Updated";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.addProject = function() {
            $http.post('http://localhost:8888/projects', vm.project)

            .success(function(result) {
                vm.project = null;
                vm.projectForm.$setPristine();
                vm.message = "Project Created";
                vm.init();
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.submit = function() {
            if (vm.edit){
                vm.editProject();
            } else {
                vm.addProject();
            }
        };

        vm.init();
    }
})();