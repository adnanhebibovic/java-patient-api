package com.test.transferfhir.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.transferfhir.entites.PatientEntity;
import com.test.transferfhir.services.PatientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllorTests {
    @Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientService service;

	@Test
	void controllorShouldReturnEntityFromService() throws Exception {
		
		var alanFord = new PatientEntity();
		alanFord.setFirstName("Alan");
		alanFord.setLastName("Ford");

		when(service.getPatients("https://en.wikipedia.org/wiki/Alan_Ford_(comics)"))
			.thenReturn(Arrays.asList(alanFord));
		
        String payload = new ObjectMapper().writeValueAsString(alanFord);

        this.mockMvc.perform(get("/getHfirPatient?url=https://en.wikipedia.org/wiki/Alan_Ford_(comics)"))
            .andDo(print())
            .andExpect(status().isOk())
		    .andExpect(content().string(containsString(payload)));
	}
}
