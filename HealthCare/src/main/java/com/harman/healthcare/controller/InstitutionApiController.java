package com.harman.healthcare.controller;

import com.harman.healthcare.model.Institution;
import com.harman.healthcare.service.InstitutionService;
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
public class InstitutionApiController {

	public static final Logger logger = LoggerFactory.getLogger(InstitutionApiController.class);

	@Autowired
	InstitutionService institutionService;

	/**
	 * Retrieve All Institutions
	 * @return
	 */
	@RequestMapping(value = "/institution/", method = RequestMethod.GET)
	public ResponseEntity<List<Institution>> listAllInstitutions() {
		List<Institution> institutions = institutionService.findAllInstitutions();
		if (institutions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Institution>>(institutions, HttpStatus.OK);
	}

	/**
	 * Retrieve Single Institution
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/institution/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInstitution(@PathVariable("id") long id) {
		Institution institution = institutionService.findById(id);
		if (institution == null) {
			return new ResponseEntity(new CustomErrorType("Institution with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Institution>(institution, HttpStatus.OK);
	}

	/**
	 * Create a Institution
	 * @param institution
	 * @param ucBuilder
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/institution/", method = RequestMethod.POST)
	public ResponseEntity<?> createInstitution(@RequestBody Institution institution, UriComponentsBuilder ucBuilder) {
		if (institutionService.isInstitutionExist(institution)) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Institution with name " +
			institution.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		institutionService.saveInstitution(institution);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/institution/{id}").buildAndExpand(institution.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a Institution
	 * @param id
	 * @param institution
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/institution/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateInstitution(@PathVariable("id") long id, @RequestBody Institution institution) {
		Institution currentInstitution = institutionService.findById(id);
		if (currentInstitution == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Institution with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentInstitution.setName(institution.getName());
		currentInstitution.setDesc(institution.getDesc());
		institutionService.updateInstitution(currentInstitution);
		return new ResponseEntity<Institution>(currentInstitution, HttpStatus.OK);
	}

	/**
	 * Delete a Institution
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/institution/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInstitution(@PathVariable("id") long id) {
		Institution institution = institutionService.findById(id);
		if (institution == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Institution with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		institutionService.deleteInstitutionById(id);
		return new ResponseEntity<Institution>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All Institutions
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/institution/", method = RequestMethod.DELETE)
	public ResponseEntity<Institution> deleteAllInstitutions() {
		institutionService.deleteAllInstitutions();
		return new ResponseEntity<Institution>(HttpStatus.NO_CONTENT);
	}

}