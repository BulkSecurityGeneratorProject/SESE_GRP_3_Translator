(function() {
    'use strict';

    angular
        .module('seseTranslatorApp')
        .controller('ProjectDeleteController',ProjectDeleteController);

    ProjectDeleteController.$inject = ['$uibModalInstance', 'project', 'Project'];

    function ProjectDeleteController($uibModalInstance, project, Project) {
        var vm = this;

        vm.project = project;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Project.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
