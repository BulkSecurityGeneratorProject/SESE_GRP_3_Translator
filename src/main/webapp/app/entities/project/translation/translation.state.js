(function () {
    'use strict';

    angular
        .module('seseTranslatorApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('project-detail.translation', {
                parent: 'project-detail',
                url: '/translation',
                params: {
                    curReleaseId: null
                },
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Translation'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/project/translation/translation.html',
                        controller: 'TranslatorController',
                        controllerAs: 'vm'
                    }
                },
                resolve: {
                    project: ['$stateParams', 'Project', function($stateParams, Project) {
                        return Project.get({id : $stateParams.projectId}).$promise;
                    }],
                    currentRelease: ['$stateParams', 'Release', function ($stateParams, Release) {
                        return Release.get({id: $stateParams.curReleaseId}).$promise;
                    }],
                    language:['$stateParams', function ($stateParams) { //todo expand when more languages
                        return {
                            id:1,
                            code:'Deutsch'
                        };
                    }],

                    nextTranslation:['$stateParams','NextTranslation', function ($stateParams,NextTranslation) {
                        return NextTranslation.query(
                            {
                                releaseId:$stateParams.curReleaseId,
                                languageId:1
                            }
                        ).$promise;
                    }],
                    previousState: ["$state", function ($state) {
                        var currentStateData = {
                            name: $state.current.name || 'project-detail',
                            params: $state.params,
                            url: $state.href($state.current.name, $state.params)
                        };
                        // fix for issue of sub pages overriding the correct previous sate in modal dialogs via 'reload' current page
                        if (currentStateData.name.startsWith('translation')) {
                            currentStateData.name = 'project-detail'
                        }
                        return currentStateData;
                    }]


                }
            });

    }

})();