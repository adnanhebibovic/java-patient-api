package com.test.transferfhir.entites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.repositories.PatientRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    PatientRepository patientRepository;
    
    @Test
    @Transactional
    public void shouldFoundPatientsByUrl() {
        PatientEntity bilboBaggins = new PatientEntity();
        bilboBaggins.setFirstName("Bilbo");
        bilboBaggins.setLastName("Baggins");
        bilboBaggins.setUrl("https://lotr.fandom.com/wiki/Bilbo_Baggins");

        patientRepository.save(bilboBaggins);

        PatientEntity gandalf = new PatientEntity();
        gandalf.setFirstName("Gandalf");
        gandalf.setLastName("the Grey");
        gandalf.setUrl("https://lotr.fandom.com/wiki/Gandalf");

        patientRepository.save(gandalf);

        var patients = patientRepository.findByUrl("https://lotr.fandom.com/wiki/Gandalf");

        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals("Gandalf", patients.get(0).getFirstName());
    }
}
