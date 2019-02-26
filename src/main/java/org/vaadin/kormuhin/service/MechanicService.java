package org.vaadin.kormuhin.service;

import com.vaadin.data.util.BeanItemContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.kormuhin.model.Mechanic;
import org.vaadin.kormuhin.repository.MechanicRepository;

@Service
public class MechanicService {
    @Autowired
    MechanicRepository mechanicRepository;

    public BeanItemContainer<Mechanic> containerMechanic() {
        BeanItemContainer<Mechanic> container = new BeanItemContainer<>(Mechanic.class, mechanicRepository.findAll());

        return container;

    }

    public void deleteMechanic(Mechanic mechanic) {
        mechanicRepository.delete(mechanic);
    }

    public void saveMechanic(Mechanic mechanic) {
        mechanicRepository.save(mechanic);
    }


}
