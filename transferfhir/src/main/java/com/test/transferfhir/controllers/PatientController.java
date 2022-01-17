package com.test.transferfhir.controllers;

import java.util.List;

import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/transferHfirPatient")
    public ResponseEntity<PatientEntity> transferHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {
        
        List<PatientEntity> patients = patientService.getPatients(url);

        if (!patients.isEmpty())
            return new ResponseEntity<>(patients.get(0), HttpStatus.FOUND);

        return new ResponseEntity<>(patientService.savePatient(url), HttpStatus.OK);
    }

    @GetMapping("/getHfirPatient")
    public ResponseEntity<PatientEntity> getHfirPatient(
            @RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {

        List<PatientEntity> patients = patientService.getPatients(url);

        if (patients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(patients.get(0), HttpStatus.OK);
    }
}
