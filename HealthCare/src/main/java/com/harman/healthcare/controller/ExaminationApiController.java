package com.harman.healthcare.controller;

import com.harman.healthcare.model.Examination;
import com.harman.healthcare.service.ExaminationService;
import com.harman.healthcare.util.ComputeBMI;
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
public class ExaminationApiController {

	public static final Logger LOG = LoggerFactory.getLogger(ExaminationApiController.class);

	@Autowired
	ExaminationService examinationService;

	/**
	 * Retrieve All Examinations
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/examination/", method = RequestMethod.GET)
	public ResponseEntity<List<Examination>> listAllExaminations() {
		List<Examination> examinations = examinationService.findAllExaminations();
		if (examinations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Examination>>(examinations, HttpStatus.OK);
	}

	/**
	 * Retrieve Single Examination
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/examination/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExamination(@PathVariable("id") long id) {
		Examination examination = examinationService.findById(id);
		ComputeBMI computeBMI=new ComputeBMI();
		computeBMI.calBMI(examination);
		if (examination == null) {
			return new ResponseEntity(new CustomErrorType("Examination with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Examination>(examination, HttpStatus.OK);
	}

	/**
	 * Create a Examination
	 * @param examination
	 * @param ucBuilder
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/examination/", method = RequestMethod.POST)
	public ResponseEntity<?> createExamination(@RequestBody Examination examination, UriComponentsBuilder ucBuilder) {
		if (examinationService.isExaminationExist(examination)) {
			return new ResponseEntity(new CustomErrorType("Unable to create. A Examination with name " +
			examination.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		examinationService.saveExamination(examination);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/examination/{id}").buildAndExpand(examination.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a Examination
	 * @param id
	 * @param examination
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/examination/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExamination(@PathVariable("id") long id, @RequestBody Examination examination) {
		Examination currentExamination = examinationService.findById(id);
		if (currentExamination == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate.id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentExamination.setName(examination.getName());
		currentExamination.setDesc(examination.getDesc());
		currentExamination.setExamDate(examination.getExamDate());
		currentExamination.setWeight(examination.getWeight());
		currentExamination.setHeight(examination.getHeight());

		examinationService.updateExamination(currentExamination);
		return new ResponseEntity<Examination>(currentExamination, HttpStatus.OK);
	}

	/**
	 * Delete a Examination
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/examination/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExamination(@PathVariable("id") long id) {
		Examination examination = examinationService.findById(id);
		if (examination == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Examination with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		examinationService.deleteExaminationById(id);
		return new ResponseEntity<Examination>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All Examinations
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/examination/", method = RequestMethod.DELETE)
	public ResponseEntity<Examination> deleteAllExaminations() {
		examinationService.deleteAllExaminations();
		return new ResponseEntity<Examination>(HttpStatus.NO_CONTENT);
	}

}