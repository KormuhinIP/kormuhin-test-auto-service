package org.vaadin.kormuhin.service;

import com.vaadin.data.util.BeanItemContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.kormuhin.model.OrderAuto;
import org.vaadin.kormuhin.repository.OrderAutoRepository;

@Service
public class OrderAutoService {
    @Autowired
    OrderAutoRepository orderAutoRepository;

    public BeanItemContainer<OrderAuto> containerOrder() {
        BeanItemContainer<OrderAuto> container = new BeanItemContainer<>(OrderAuto.class, orderAutoRepository.findAll());
        return container;
    }

    public void deleteOrder(OrderAuto order) {
        orderAutoRepository.delete(order);
    }

    public void saveOrder(OrderAuto order) {
        orderAutoRepository.save(order);
    }


}
