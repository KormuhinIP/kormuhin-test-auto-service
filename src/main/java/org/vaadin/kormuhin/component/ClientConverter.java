package org.vaadin.kormuhin.component;

import com.vaadin.data.util.converter.Converter;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.model.Client;

import java.util.Locale;

@Component
public class ClientConverter implements Converter<String, Client> {

    @Override
    public Client convertToModel(String s, Class<? extends Client> aClass, Locale locale) throws
            Converter.ConversionException {
        return null;
    }

    @Override
    public String convertToPresentation(Client client, Class<? extends String> aClass, Locale locale) throws
            Converter.ConversionException {
        if (client == null)
            return null;
        return client.getLastName() + " тел. " + client.getNumberPhone();
    }

    @Override
    public Class<Client> getModelType() {
        return Client.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}

