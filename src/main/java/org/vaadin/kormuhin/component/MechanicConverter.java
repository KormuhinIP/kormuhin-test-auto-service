package org.vaadin.kormuhin.component;

import com.vaadin.data.util.converter.Converter;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.Mechanic;

import java.util.Locale;

@Component
public class MechanicConverter implements Converter<String, Mechanic> {

    @Override
    public Mechanic convertToModel(String s, Class<? extends Mechanic> aClass, Locale locale) throws
            ConversionException {
        return null;
    }

    @Override
    public String convertToPresentation(Mechanic mechanic, Class<? extends String> aClass, Locale locale) throws
            ConversionException {
        if (mechanic == null)
            return null;
        return mechanic.getLastName() + " " + mechanic.getFirstName();
    }

    @Override
    public Class<Mechanic> getModelType() {
        return Mechanic.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}

