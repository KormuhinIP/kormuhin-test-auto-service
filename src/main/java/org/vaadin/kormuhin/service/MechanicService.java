package org.vaadin.kormuhin.service;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ListSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.repository.MechanicRepository;

import java.util.ArrayList;

@Service
public class MechanicService {
    @Autowired
    MechanicRepository mechanicRepository;

    public BeanItemContainer<Mechanic> containerMechanic() {
        BeanItemContainer<Mechanic> container = new BeanItemContainer<>(Mechanic.class, mechanicRepository.findAll());
        container.addItemSetChangeListener(new ListSelect("firstName"));
        return container;

    }

    public ArrayList<Integer> listOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Mechanic mechanic : mechanicRepository.findAll()) {
            list.add(mechanic.getOrderAutos());
        }
        return list;
    }

    public ArrayList<String> listLastName() {
        ArrayList<String> list = new ArrayList<>();
        for (Mechanic mechanic : mechanicRepository.findAll()) {
            list.add(mechanic.getLastName());
        }
        return list;
    }

    public void deleteMechanic(Mechanic mechanic) {
        mechanicRepository.delete(mechanic);
    }

    public void saveMechanic(Mechanic mechanic) {
        mechanicRepository.save(mechanic);
    }

}
