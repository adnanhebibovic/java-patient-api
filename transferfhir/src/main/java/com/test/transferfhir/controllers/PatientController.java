package com.test.transferfhir.controllers;

import com.test.transferfhir.classes.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PatientController {

	@Autowired
    private RestTemplate restTemplate;

	@GetMapping("/patient")
	public Patient getHfirPatient(@RequestParam(value = "url", defaultValue = "https://hapi.fhir.org/baseR4/Patient/1854776") String url) {
        Patient patient = restTemplate.getForObject(url, Patient.class);

        return patient;
	}
}
