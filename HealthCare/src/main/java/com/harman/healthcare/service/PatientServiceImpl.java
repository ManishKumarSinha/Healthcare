package com.harman.healthcare.service;

import com.harman.healthcare.model.Patient;
import com.harman.healthcare.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;



@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService{

	@Autowired
	private PatientRepository patientRepository;

	public Patient findById(Long id) {
		return patientRepository.findOne(id);
	}

	public Patient findByName(String name) {
		return patientRepository.findByName(name);
	}

	public void saveUser(Patient patient) {
		patientRepository.save(patient);
	}

	public void updateUser(Patient patient){
		saveUser(patient);
	}

	public void deletePatientById(Long id){
		patientRepository.delete(id);
	}

	public void deleteAllPatients(){
		patientRepository.deleteAll();
	}

	public List<Patient> findAllPatients(){
		return patientRepository.findAll();
	}

	public boolean isUserExist(Patient patient) {
		return findByName(patient.getName()) != null;
	}

	@Override
	public void savePatient(Patient patient) {
		// TODO Auto-generated method stub
		patientRepository.save(patient);
	}

	@Override
	public boolean isPatientExist(Patient patient) {
		// TODO Auto-generated method stub
		return findByName(patient.getName()) != null;
	}

	@Override
	public void updatePatient(Patient patient) {
		savePatient(patient);
		
	}

	@Override
	public Integer findByDOB(Date dob) {
		Date now = new Date();
		long timeBetween = now.getTime() - dob.getTime();
		double yearsBetween = timeBetween / 3.156e+10;
		int age = (int) Math.floor(yearsBetween);
		return age;
	}

}
