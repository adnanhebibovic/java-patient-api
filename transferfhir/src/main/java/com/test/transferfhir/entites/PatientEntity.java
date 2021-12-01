package com.test.transferfhir.entites;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class PatientEntity extends BaseEntity {

    @Column(unique=true)
    @NotEmpty
    private String url;
    
    private String prefix;
    private String suffix;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        var result = "Patient {";

        result += prefix != null ? " prefix='" + prefix + "'" : "";
        result += firstName != null ? " firstName='" + firstName + "'" : "";
        result += lastName != null ? " lastName='" + lastName + "'" : "";
        result += suffix != null ? " suffix='" + suffix + "'" : "";
        result += gender != null ? " gender='" + gender + "'" : "";
        result += birthDate != null ? " birthDate='" + birthDate.toString() + "'" : "";

        result += "}";

        return result;
    }
}
