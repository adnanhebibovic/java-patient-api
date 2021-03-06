package com.test.transferfhir.entites;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTests {

    @Test
	void shouldNotValidateWhenUrlIsEmpty() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        LocaleContextHolder.setLocale(Locale.ENGLISH);
		PatientEntity patient = new PatientEntity();
		patient.setUrl("");

		Set<ConstraintViolation<PatientEntity>> constraintViolations = validator.validate(patient);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<PatientEntity> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).hasToString("url");
		assertThat(violation.getMessage()).hasToString("must not be empty");
	}
}
