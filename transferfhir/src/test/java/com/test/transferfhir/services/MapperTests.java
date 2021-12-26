package com.test.transferfhir.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapperTests {

    @Autowired
    private PatientMapper patientMapper;
    
    @Test
    void shouldMapEmptyPatient() {
        PatientEntity patient = patientMapper.map(new Patient());

        assertNotNull(patient);
    }
}
