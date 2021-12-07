package com.test.transferfhir.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import com.test.transferfhir.classes.Name;
import com.test.transferfhir.classes.Patient;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ServiceTests {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void givenMockito_whenGetIsCalled_thenReturnMockObject()
    {
        Name[] name = { new Name() };
        name[0].setFamily("Picasso");

        Patient givenPatient = new Patient();
        givenPatient.setName(Arrays.asList(name));

        Mockito
        .when(restTemplate.getForEntity("https://en.wikipedia.org/wiki/Pablo_Picasso", Patient.class))
        .thenReturn(new ResponseEntity<>(givenPatient, HttpStatus.OK));

        Patient actualPatient = patientService.getPatient("https://en.wikipedia.org/wiki/Pablo_Picasso");

        assertEquals(givenPatient.getLastName(), actualPatient.getLastName());
    }
}