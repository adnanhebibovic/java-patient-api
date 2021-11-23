package com.test.transferfhir.repositories;

import java.util.List;

import com.test.transferfhir.entites.Patient;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    List<Patient> findByLastName(String lastName);

    Patient findById(long id);
}
