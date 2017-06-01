package com.harman.healthcare.service;

import com.harman.healthcare.model.Examination;
import com.harman.healthcare.repositories.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service("examinationService")
@Transactional
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	private ExaminationRepository examinationRepository;

	public Examination findById(Long id) {
		return examinationRepository.findOne(id);
	}

	public Examination findByName(String name) {
		return examinationRepository.findByName(name);
	}

	public void saveExamination(Examination examination) {
		examinationRepository.save(examination);
	}

	public void updateExamination(Examination examination){
		saveExamination(examination);
	}

	public void deleteExaminationById(Long id){
		examinationRepository.delete(id);
	}

	public void deleteAllExaminations(){
		examinationRepository.deleteAll();
	}

	public List<Examination> findAllExaminations(){
		return examinationRepository.findAll();
	}

	public boolean isExaminationExist(Examination examination) {
		return findByName(examination.getName()) != null;
	}

}
