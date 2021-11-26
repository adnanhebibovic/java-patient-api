package com.test.transferfhir.entites;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.Provider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.test.transferfhir.classes.Patient;

@Service
public class PatientMapper {
    
    public PatientEntity map(Patient patient) {
        
        ModelMapper modelMapper = new ModelMapper();

        Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };

        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        PropertyMap <Patient, PatientEntity> patientMap = 
            new PropertyMap <Patient,PatientEntity>() {

                protected void configure() {
                    
                    //cannot execute complex custom logic directly within PropertyMap.configure()
                    map().setLastName(source.getLastName());
                    map().setFirstName(source.getFirstName());
                    map().setPrefix(source.getPrefix());
                    map().setSuffix(source.getSuffix());
                    
                }
        };
          
        modelMapper.addMappings(patientMap);

        return modelMapper.map(patient, PatientEntity.class);
    }
}
