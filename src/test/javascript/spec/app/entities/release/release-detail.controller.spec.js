'use strict';

describe('Controller Tests', function() {

    describe('Release Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockRelease, MockDefinition, MockLanguage, MockProject;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockRelease = jasmine.createSpy('MockRelease');
            MockDefinition = jasmine.createSpy('MockDefinition');
            MockLanguage = jasmine.createSpy('MockLanguage');
            MockProject = jasmine.createSpy('MockProject');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Release': MockRelease,
                'Definition': MockDefinition,
                'Language': MockLanguage,
                'Project': MockProject
            };
            createController = function() {
                $injector.get('$controller')("ReleaseDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'seseTranslatorApp:releaseUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
