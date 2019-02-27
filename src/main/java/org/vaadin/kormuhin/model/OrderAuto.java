package org.vaadin.kormuhin.model;

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
    private String mechanicName;
    private String clientName;
    private String statusOrder;

    @ManyToOne(/*fetch=FetchType.LAZY*/ cascade = CascadeType.REFRESH)
    private Mechanic mechanic;

    @ManyToOne(/*fetch=FetchType.LAZY*/ cascade = CascadeType.REFRESH)
    private Client client;



}


