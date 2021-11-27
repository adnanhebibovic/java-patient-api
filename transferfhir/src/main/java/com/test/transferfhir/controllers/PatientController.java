package com.test.transferfhir.controllers;

import java.util.List;

import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.entites.PatientMapper;
import com.test.transferfhir.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PatientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    private List<PatientEntity> getPatientsByUrl(String url) {
        return patientRepository.findByUrl(url);
    }

    @PostMapping("/transferHfirPatient")
    public ResponseEntity<PatientEntity> transferHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {
        PatientEntity patient = patientMapper.map(restTemplate.getForObject(url, Patient.class));

        patient.setUrl(url);

        synchronized (this) {
            List<PatientEntity> patients = getPatientsByUrl(url);

            if (!patients.isEmpty())
                return new ResponseEntity<>(patients.get(0), HttpStatus.FOUND);

            return new ResponseEntity<>(patientRepository.save(patient), HttpStatus.CREATED);
        }
    }

    @GetMapping("/getHfirPatient")
    public ResponseEntity<PatientEntity> getHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {

        List<PatientEntity> patients = getPatientsByUrl(url);

        if (patients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(patients.get(0), HttpStatus.OK);
    }
}
