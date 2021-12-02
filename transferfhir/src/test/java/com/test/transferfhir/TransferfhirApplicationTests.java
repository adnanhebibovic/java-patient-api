package com.test.transferfhir;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.repositories.PatientRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferfhirApplicationTests {

	@Autowired
	PatientRepository patientRepository;

	@Test
	void contextLoads() {
		assertNotNull(patientRepository);
	}

}
