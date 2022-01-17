package com.test.transferfhir.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import javax.transaction.Transactional;

import com.test.transferfhir.classes.Name;
import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.repositories.PatientRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ServiceTests {
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    @Spy
    private PatientMapper patientMapper;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    @Transactional
    void givenMockito_whenGetIsCalled_thenReturnMockObject()
    {
        Name name = new Name();
        name.setFamily("Picasso");
        name.setGiven(Arrays.asList(new String[] {"Pablo"}));

        Patient givenPatient = new Patient();
        givenPatient.setName(Arrays.asList(new Name[] { name }));
        givenPatient.setBirthDate("1881-10-25");

        Mockito
            .when(restTemplate.getForEntity("https://en.wikipedia.org/wiki/Pablo_Picasso", Patient.class))
            .thenReturn(new ResponseEntity<>(givenPatient, HttpStatus.OK));  
        
        Mockito
            .when(patientRepository.save(Mockito.any(PatientEntity.class)))
            .thenAnswer(i -> i.getArguments()[0]);

        PatientEntity savedEntity = patientService.savePatient("https://en.wikipedia.org/wiki/Pablo_Picasso");

        assertNotNull(savedEntity);

        assertEquals(givenPatient.getLastName(), savedEntity.getLastName());
    }
}
