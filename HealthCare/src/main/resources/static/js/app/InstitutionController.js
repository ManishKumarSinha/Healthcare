'use strict';

angular.module('crudApp').controller('InstitutionController',
    ['InstitutionService', '$scope',  function( InstitutionService, $scope) {

        var self = this;
        self.institution = {};
        self.institutions=[];

        self.submit = submit;
        self.getAllInstitutions = getAllInstitutions;
        self.createInstitution = createInstitution;
        self.updateInstitution = updateInstitution;
        self.removeInstitution = removeInstitution;
        self.editInstitution = editInstitution;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.institution.id === undefined || self.institution.id === null) {
                console.log('Saving New Institution', self.institution);
                createInstitution(self.institution);
            } else {
                updateInstitution(self.institution, self.institution.id);
                console.log('Institution updated with id ', self.institution.id);
            }
        }

        function createInstitution(institution) {
            console.log('About to create institution');
            InstitutionService.createInstitution(institution)
                .then(
                    function (response) {
                        console.log('Institution created successfully');
                        self.successMessage = 'Institution created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.institution={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Institution');
                        self.errorMessage = 'Error while creating Institution: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateInstitution(institution, id){
            console.log('About to update institution');
            InstitutionService.updateInstitution(institution, id)
                .then(
                    function (response){
                        console.log('Institution updated successfully');
                        self.successMessage='Institution updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Institution');
                        self.errorMessage='Error while updating Institution '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeInstitution(id){
            console.log('About to remove Institution with id '+id);
            InstitutionService.removeInstitution(id)
                .then(
                    function(){
                        console.log('Institution '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing institution '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllInstitutions(){
            return InstitutionService.getAllInstitutions();
        }

        function editInstitution(id) {
            self.successMessage='';
            self.errorMessage='';
            InstitutionService.getInstitution(id).then(
                function (institution) {
                    self.institution = institution;
                },
                function (errResponse) {
                    console.error('Error while removing institution ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.institution={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);