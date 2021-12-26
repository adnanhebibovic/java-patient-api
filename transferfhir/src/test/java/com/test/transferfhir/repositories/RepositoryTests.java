package com.test.transferfhir.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.test.transferfhir.entites.PatientEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RepositoryTests {

    @Autowired
    PatientRepository patientRepository;
    
    @Test
    @Transactional
    void shouldFoundPatientsByUrl() {
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

    @Test
    @Transactional
    void shouldNotSavePatientsWithDuplicateUrl() {
        PatientEntity theGray = new PatientEntity();
        theGray.setFirstName("Gandalf");
        theGray.setLastName("the Grey");
        theGray.setUrl("https://lotr.fandom.com/wiki/Gandalf");

        patientRepository.save(theGray);
        
        PatientEntity theWhite = new PatientEntity();
        theWhite.setFirstName("Gandalf");
        theWhite.setLastName("the White");
        theWhite.setUrl("https://lotr.fandom.com/wiki/Gandalf");

        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            patientRepository.save(theWhite);
        });

        assertNotNull(dataIntegrityViolationException);
        assertTrue(dataIntegrityViolationException.getMessage().contains("ConstraintViolationException"));
    }
}
