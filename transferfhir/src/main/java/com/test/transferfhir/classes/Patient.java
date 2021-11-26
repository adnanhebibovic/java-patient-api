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

    private Name getFirstOccurrenceOfName() {
        List<Name> names = this.getName();

        Name name = names != null && !names.isEmpty() ? names.get(0) : null;

        return name;
    }

    public String getLastName() {
        Name name = getFirstOccurrenceOfName();
        
        String lastName = name != null ? name.getFamily() : null;

        return lastName;
    }

    public String getFirstName() {
        Name name = getFirstOccurrenceOfName();

        String firstName = name != null && name.getGiven() != null && !name.getGiven().isEmpty() ?
            String.join(" ", name.getGiven()) :
            null;

        return firstName;
    }

    public String getPrefix() {
        Name name = getFirstOccurrenceOfName();

        String prefix = name != null && name.getPrefix() != null && !name.getPrefix().isEmpty() ?
            String.join(" ", name.getPrefix()) :
            null;

        return prefix;
    }

    public String getSuffix() {
        Name name = getFirstOccurrenceOfName();

        String suffix = name != null && name.getSuffix() != null && !name.getSuffix().isEmpty() ?
        String.join(" ", name.getSuffix()) :
        null;

        return suffix;
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