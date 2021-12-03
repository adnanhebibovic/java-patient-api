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

        return names != null && !names.isEmpty() ? names.get(0) : null;
    }

    public String getLastName() {
        Name fullName = getFirstOccurrenceOfName();
        
        return fullName != null ? fullName.getFamily() : null;
    }

    public String getFirstName() {
        Name fullName = getFirstOccurrenceOfName();

        return fullName != null && fullName.getGiven() != null && !fullName.getGiven().isEmpty() ?
            String.join(" ", fullName.getGiven()) :
            null;
    }

    public String getPrefix() {
        Name fullName = getFirstOccurrenceOfName();

        return fullName != null && fullName.getPrefix() != null && !fullName.getPrefix().isEmpty() ?
            String.join(" ", fullName.getPrefix()) :
            null;
    }

    public String getSuffix() {
        Name fullName = getFirstOccurrenceOfName();

        return fullName != null && fullName.getSuffix() != null && !fullName.getSuffix().isEmpty() ?
        String.join(" ", fullName.getSuffix()) :
        null;
    }

    @Override
    public String toString() {
        String result = "Patient {";
        
        if (name != null && !name.isEmpty())
            result += "name='" + name.toString() + "'";

        if (gender != null && !"".equals(gender))
            result += ", gender='" + gender + "'";

        if (birthDate != null && !"".equals(birthDate))
            result += ", birthDate= '" + birthDate + "'";
        
        result += "}";

        return result;
    }
}