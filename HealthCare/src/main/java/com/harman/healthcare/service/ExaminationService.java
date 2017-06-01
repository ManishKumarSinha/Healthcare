package com.harman.healthcare.service;


import com.harman.healthcare.model.Examination;

import java.util.List;

public interface ExaminationService {
	
	Examination findById(Long id);

	Examination findByName(String name);

	void saveExamination(Examination examination);

	void updateExamination(Examination examination);

	void deleteExaminationById(Long id);

	void deleteAllExaminations();

	List<Examination> findAllExaminations();

	boolean isExaminationExist(Examination examination);
}