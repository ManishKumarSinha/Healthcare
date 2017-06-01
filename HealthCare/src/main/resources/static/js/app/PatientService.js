'use strict';

angular.module('crudApp').factory('PatientService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllPatients: loadAllPatients,
                getAllPatients: getAllPatients,
                getPatient: getPatient,
                createPatient: createPatient,
                updatePatient: updatePatient,
                removePatient: removePatient
            };

            return factory;

            function loadAllPatients() {
                console.log('Fetching all patients');
                var deferred = $q.defer();
                $http.get(urls.PATIENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all patients');
                            $localStorage.patients = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading patients');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPatients(){
                return $localStorage.patients;
            }

            function getPatient(id) {
                console.log('Fetching Patient with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PATIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Patient with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPatient(patient) {
                console.log('Creating Patient');
                var deferred = $q.defer();
                $http.post(urls.PATIENT_SERVICE_API, patient)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Patient : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updatePatient(patient, id) {
                console.log('Updating Patient with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PATIENT_SERVICE_API + id, patient)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removePatient(id) {
                console.log('Removing Patient with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PATIENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Patient with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);