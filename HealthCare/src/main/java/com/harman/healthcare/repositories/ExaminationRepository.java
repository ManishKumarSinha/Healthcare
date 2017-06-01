package com.harman.healthcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harman.healthcare.model.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	Examination findByName(String name);

}
