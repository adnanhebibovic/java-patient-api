package com.test.transferfhir.entites;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.classes.Patient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTests {

    @Autowired
    private PatientMapper patientMapper;
    
    @Test
    public void shouldMapEmptyPatient() {
        PatientEntity patient = patientMapper.map(new Patient());

        assertNotNull(patient);
    }
}
