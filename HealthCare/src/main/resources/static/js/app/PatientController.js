'use strict';

angular.module('crudApp').controller('PatientController',
    ['PatientService', '$scope',  function( PatientService, $scope) {

        var self = this;
        self.patient = {};
        self.patients=[];

        self.submit = submit;
        self.getAllPatients = getAllPatients;
        self.createPatient = createPatient;
        self.updatePatient = updatePatient;
        self.removePatient = removePatient;
        self.editPatient = editPatient;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.patient.id === undefined || self.patient.id === null) {
                console.log('Saving New Patient', self.patient);
                createPatient(self.patient);
            } else {
                updatePatient(self.patient, self.patient.id);
                console.log('Patient updated with id ', self.patient.id);
            }
        }

        function createPatient(patient) {
            console.log('About to create patient');
            PatientService.createPatient(patient)
                .then(
                    function (response) {
                        console.log('Patient created successfully');
                        self.successMessage = 'Patient created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.patient={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Patient');
                        self.errorMessage = 'Error while creating Patient: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updatePatient(patient, id){
            console.log('About to update patient');
            PatientService.updatePatient(patient, id)
                .then(
                    function (response){
                        console.log('Patient updated successfully');
                        self.successMessage='Patient updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Patient');
                        self.errorMessage='Error while updating Patient '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removePatient(id){
            console.log('About to remove Patient with id '+id);
            PatientService.removePatient(id)
                .then(
                    function(){
                        console.log('Patient '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing patient '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllPatients(){
            return PatientService.getAllPatients();
        }

        function editPatient(id) {
            self.successMessage='';
            self.errorMessage='';
            PatientService.getPatient(id).then(
                function (patient) {
                    self.patient = patient;
                },
                function (errResponse) {
                    console.error('Error while removing patient ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.patient={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);