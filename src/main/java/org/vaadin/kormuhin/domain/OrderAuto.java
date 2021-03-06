package org.vaadin.kormuhin.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@ToString

public class OrderAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Date dateCreate;
    private Date dateCompletion;
    private Double cost;
    private String statusOrder;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Mechanic mechanic;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Client client;



}


