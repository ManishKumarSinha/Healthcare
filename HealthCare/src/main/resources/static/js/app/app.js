var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8200/healthcare',
    INSTITUTION_SERVICE_API : 'http://localhost:8200/healthcare/api/institution/',
    PATIENT_SERVICE_API : 'http://localhost:8200/healthcare/api/patient/'
});

/*var app = angular.module("crudApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : 'partials/list',
        controller:'InstitutionController',
        controllerAs:'ctrl'
    })
    .when("/london", {
        templateUrl : "london.htm",
        controller:'PatientController',
        controllerAs:'patientctrl'
    })
});*/

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

	 $urlRouterProvider.otherwise('/');
	 
        $stateProvider
           
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'InstitutionController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, InstitutionService) {
                        console.log('Load all institutions');
                        var deferred = $q.defer();
                        InstitutionService.loadAllInstitutions().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })

            .state('home1', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'PatientController',
                controllerAs:'patientctrl',
                resolve: {
                    users: function ($q, PatientService) {
                        console.log('Load all patients');
                        var deferred = $q.defer();
                        PatientService.loadAllPatients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
}]);

