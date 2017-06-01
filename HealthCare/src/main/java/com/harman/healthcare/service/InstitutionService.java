package com.harman.healthcare.service;


import com.harman.healthcare.model.Institution;

import java.util.List;

public interface InstitutionService {
	
	Institution findById(Long id);

	Institution findByName(String name);

	void saveInstitution(Institution institution);

	void updateInstitution(Institution institution);

	void deleteInstitutionById(Long id);

	void deleteAllInstitutions();

	List<Institution> findAllInstitutions();

	boolean isInstitutionExist(Institution institution);
}