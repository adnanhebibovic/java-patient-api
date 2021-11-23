package com.test.transferfhir.classes;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    
    private String family;
    private List<String> given = null;
    private List<String> prefix = null;
    private List<String> suffix = null;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getGiven() {
        return given;
    }

    public void setGiven(List<String> given) {
        this.given = given;
    }

    public List<String> getPrefix() {
        return prefix;
    }

    public void setPrefix(List<String> prefix) {
        this.prefix = prefix;
    }

    public List<String> getSuffix() {
        return suffix;
    }

    public void setSuffix(List<String> suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        String result = "Name {";

        if (family != null && family != "")
            result +=  "family='" + family + "'";
            
        if (given != null && !given.isEmpty())
            result += ", given='" + given.toString() + "'";
        
        if (prefix != null && !prefix.isEmpty())
            result += ", prefix='" + prefix.toString() + "'";
            
        if (suffix != null && !suffix.isEmpty())
            result += ", suffix='" + suffix.toString() + "'";
            
        result += "}";

        return result;
    }
}