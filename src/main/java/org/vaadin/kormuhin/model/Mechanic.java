package org.vaadin.kormuhin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;


@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Mechanic extends BasePeople {
    private double hourlyPay;
    private Integer numberOrders;

}


