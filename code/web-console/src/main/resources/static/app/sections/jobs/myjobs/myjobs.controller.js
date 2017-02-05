(function() {
    'use strict';

    angular
        .module('myjobs.core', [])
        .controller('MyJobsController', controller);

    /** @ngInject */
    function controller($http) {
        var vm = this;

        vm.init = function() {
            $http.get('http://localhost:8888/jobs/self')

            .success(function(result) {
                vm.jobs = result;
                vm.message = '';
            })

            .error(function(error) {
                vm.message = error.message;
            });
        };

        vm.openJob = function(job) {
            $http.put('http://localhost:8888/jobs/' + job.id + '/open')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.closeJob = function(job) {
            $http.put('http://localhost:8888/jobs/' + job.id + '/close')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.cancelJob = function(job) {
            $http.put('http://localhost:8888/jobs/' + job.id + '/cancel')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.disputeJob = function(job) {
            $http.put('http://localhost:8888/jobs/' + job.id + '/dispute')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.archiveJob = function(job) {
            $http.put('http://localhost:8888/jobs/' + job.id + '/archive')

            .success(function(res) {
                vm.init();
            })

            .error(function(error) {
                vm.deleteMessage = error.message;
            });
        };

        vm.init();
    }
})();