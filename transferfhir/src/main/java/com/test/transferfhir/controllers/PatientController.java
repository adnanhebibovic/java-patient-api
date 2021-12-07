package com.test.transferfhir.controllers;

import java.util.List;

import com.test.transferfhir.classes.Patient;
import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.repositories.PatientRepository;
import com.test.transferfhir.services.PatientMapper;
import com.test.transferfhir.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper patientMapper;

    private List<PatientEntity> getPatientsByUrl(String url) {
        return patientRepository.findByUrl(url);
    }

    @PostMapping("/transferHfirPatient")
    public ResponseEntity<PatientEntity> transferHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {
        
        List<PatientEntity> patients = getPatientsByUrl(url);

        if (!patients.isEmpty())
            return new ResponseEntity<>(patients.get(0), HttpStatus.FOUND);

        Patient patient = patientService.getPatient(url);

        if (patient == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url " + url + " did not work well! Please try again!");

        PatientEntity patientEntity = patientMapper.map(patient);

        patientEntity.setUrl(url);

        return new ResponseEntity<>(patientRepository.save(patientEntity), HttpStatus.CREATED);
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
