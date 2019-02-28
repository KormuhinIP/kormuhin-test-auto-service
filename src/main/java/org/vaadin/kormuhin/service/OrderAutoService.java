package org.vaadin.kormuhin.service;

import com.vaadin.data.util.BeanItemContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.kormuhin.component.OrderFilter;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.domain.OrderAuto;
import org.vaadin.kormuhin.repository.OrderAutoRepository;

import java.util.Date;

@Service
public class OrderAutoService {
    @Autowired
    OrderAutoRepository orderAutoRepository;
    @Autowired
    OrderFilter orderFilter;

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

    public int getStatistic(Mechanic mechanic, Date dateBegin, Date dateEnd) {
        return orderAutoRepository.orderAuto(mechanic, dateBegin, dateEnd).size();
    }
}



