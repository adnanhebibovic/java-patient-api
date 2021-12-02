package com.test.transferfhir;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.repositories.PatientRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class TransferfhirApplicationTests {

	@Autowired
	PatientRepository patientRepository;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		// Do any additional configuration here
		return builder.build();
	}

	@Test
	void contextLoads() {
		assertNotNull(patientRepository);
	}

}
