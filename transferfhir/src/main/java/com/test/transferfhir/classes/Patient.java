package com.test.transferfhir.classes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {

    private List<Name> name = null;
    private String gender;
    private String birthDate;

    public List<Name> getName() {
        return name;
    }

    public void setName(List<Name> name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    private Name getFirstOccureanceOfName() {
        List<Name> names = this.getName();

        Name name = names != null && !names.isEmpty() ? names.get(0) : null;

        return name;
    }

    public String getLastName() {
        Name name = getFirstOccureanceOfName();
        
        String lastName = name != null ? name.getFamily() : null;

        return lastName;
    }

    public String getFirstName() {
        Name name = getFirstOccureanceOfName();

        String firstName = name != null && !name.getGiven().isEmpty() ?
            String.join(" ", name.getGiven()) :
            null;

        return firstName;
    }

    @Override
    public String toString() {
        String result = "Patient {";
        
        if (name != null && !name.isEmpty())
            result += "name='" + name.toString() + "'";

        if (gender != null && gender != "")
            result += ", gender='" + gender + "'";

        if (birthDate != null && birthDate != "")
            result += ", birthDate= '" + birthDate + "'";
        
        result += "}";

        return result;
    }
}