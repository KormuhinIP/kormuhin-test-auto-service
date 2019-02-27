package org.vaadin.kormuhin.component;

import com.vaadin.data.util.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DoubleConverter implements Converter<String, Double> {
        @Override
        public Double convertToModel(String value, Class<? extends Double> targetType, Locale locale)
             throws ConversionException {
            if (value == null)
                return null;
            if (value.isEmpty()) {
                return 0.0;
            }
            return Double.parseDouble(value);
        }
        @Override
        public String convertToPresentation(Double value,
                Class<? extends String> targetType, Locale locale)
                    throws com.vaadin.data.util.converter.Converter.ConversionException {
            if (value == null)
                return null;
            return String.valueOf(value);
        }
    @Override
        public Class<Double> getModelType() {
            return Double.class;
        }
        @Override
        public Class<String> getPresentationType() {
            return String.class;
        }
    }

