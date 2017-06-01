package com.harman.healthcare.controller;

import com.harman.healthcare.model.Patient;
import com.harman.healthcare.service.PatientService;
import com.harman.healthcare.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientApiController {

	public static final Logger logger = LoggerFactory.getLogger(PatientApiController.class);

	@Autowired
	PatientService patientService;

	/**
	 * Retrieve All Patients
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/", method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> listAllPatients() {
		logger.info("Fetching All Patients");
		List<Patient> patients = patientService.findAllPatients();
		if (patients.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}

	/**
	 * Retrieve Single Patient
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPatient(@PathVariable("id") long id) {
		Patient patient = patientService.findById(id);
		if (patient == null) {
			return new ResponseEntity(new CustomErrorType("Patient with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	/**
	 * Create a Patient
	 * @param patient
	 * @param ucBuilder
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/", method = RequestMethod.POST)
	public ResponseEntity<?> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
		if (patientService.isPatientExist(patient)) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Patient with name " +
			patient.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		patientService.savePatient(patient);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/patient/{id}").buildAndExpand(patient.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a Patient
	 * @param id
	 * @param patient
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
		Patient currentPatient = patientService.findById(id);
		if (currentPatient == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentPatient.setName(patient.getName());
		currentPatient.setDob(patient.getDob());
		currentPatient.setGender(patient.getGender());
		patientService.updatePatient(currentPatient);
		return new ResponseEntity<Patient>(currentPatient, HttpStatus.OK);
	}

	/**
	 * Delete a Patient
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePatient(@PathVariable("id") long id) {
		Patient patient = patientService.findById(id);
		if (patient == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Patient with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		patientService.deletePatientById(id);
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All Patients
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/patient/", method = RequestMethod.DELETE)
	public ResponseEntity<Patient> deleteAllPatients() {
		patientService.deleteAllPatients();
		return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
	}

}