package com.test.transferfhir.services;

import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PatientMapper patientMapper;

    public PatientEntity getPatientEntity(String url) {
        ResponseEntity<Patient> responseEntity = restTemplate.getForEntity(url, Patient.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK)
            return null;

        PatientEntity patientEntity = patientMapper.map(responseEntity.getBody());

        patientEntity.setUrl(url);

        return patientEntity;
    }
}