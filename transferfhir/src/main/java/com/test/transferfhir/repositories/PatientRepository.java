package com.test.transferfhir.repositories;

import java.util.List;

import com.test.transferfhir.entites.PatientEntity;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity, Long> {
    List<PatientEntity> findByUrl(String url);

    PatientEntity findById(long id);
}
