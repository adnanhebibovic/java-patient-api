package com.test.transferfhir;

import com.test.transferfhir.entites.Patient;
import com.test.transferfhir.repositories.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TransferfhirApplication {

	private static final Logger log = LoggerFactory.getLogger(TransferfhirApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TransferfhirApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner demo(PatientRepository repository) {
		return (args) -> {
			repository.save(new Patient("Jack", "Bauer"));
			repository.save(new Patient("Chloe", "O'Brian"));
			repository.save(new Patient("Kim", "Bauer"));
			repository.save(new Patient("David", "Palmer"));
			repository.save(new Patient("Michelle", "Dessler"));

			log.info("Patient found with findAll():");
			log.info("-------------------------------");
			for (Patient patient : repository.findAll()) {
				log.info(patient.toString());
			}
			log.info("");

			Patient patient = repository.findById(1);
			log.info("Patient found with findById(1L):");
			log.info("--------------------------------");
			log.info(patient.toString());
			log.info("");

			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});

			log.info("");
		};
	}
}
