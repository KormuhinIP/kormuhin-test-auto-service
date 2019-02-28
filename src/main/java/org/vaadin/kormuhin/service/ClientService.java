package org.vaadin.kormuhin.service;

import com.vaadin.data.util.BeanItemContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.kormuhin.domain.Client;
import org.vaadin.kormuhin.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public BeanItemContainer<Client> containerClient() {
        BeanItemContainer<Client> container = new BeanItemContainer<>(Client.class, clientRepository.findAll());
        return container;
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }


}
