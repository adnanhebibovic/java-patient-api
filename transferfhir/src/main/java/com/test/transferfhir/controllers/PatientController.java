package com.test.transferfhir.controllers;

import java.util.List;

import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.repositories.PatientRepository;
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

    private List<PatientEntity> getPatientsByUrl(String url) {
        return patientRepository.findByUrl(url);
    }

    @PostMapping("/transferHfirPatient")
    public ResponseEntity<PatientEntity> transferHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {
        
        List<PatientEntity> patients = getPatientsByUrl(url);

        if (!patients.isEmpty())
            return new ResponseEntity<>(patients.get(0), HttpStatus.FOUND);

        PatientEntity patient = patientService.getPatientEntity(url);

        if (patient == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url " + url + " did not worked well! Please try again!");

        return new ResponseEntity<>(patientRepository.save(patient), HttpStatus.CREATED);
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
