package com.test.transferfhir;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.transferfhir.repositories.PatientRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@Configuration
class TransferfhirApplicationTests {

	@Autowired
	PatientRepository patientRepository;

	@Test
	void contextLoads() {
		assertNotNull(patientRepository);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
