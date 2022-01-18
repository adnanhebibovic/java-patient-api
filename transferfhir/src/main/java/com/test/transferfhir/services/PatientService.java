package com.test.transferfhir.services;

import java.util.List;

import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.handlers.RestTemplateErrorHandler;
import com.test.transferfhir.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public PatientMapper patientMapper;

    private ResponseEntity<Patient> getResponseEntity(String url) {
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate.getForEntity(url, Patient.class);
    }

    public PatientEntity savePatient(String url) {
        ResponseEntity<Patient> response = getResponseEntity(url);

        PatientEntity patientEntity = patientMapper.map(response.getBody());
    
        patientEntity.setUrl(url);

        return patientRepository.save(patientEntity);
    }

    public List<PatientEntity> getPatients(String url) {
        return patientRepository.findByUrl(url);
    }
}
