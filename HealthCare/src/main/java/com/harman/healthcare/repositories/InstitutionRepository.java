package com.harman.healthcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harman.healthcare.model.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

	Institution findByName(String name);

}
