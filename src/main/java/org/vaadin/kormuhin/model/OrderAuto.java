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

    private String client;
    private String mechanic;
    //  private String status;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}


