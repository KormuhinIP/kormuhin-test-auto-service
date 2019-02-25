package org.vaadin.kormuhin.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
@Data
@ToString

public class OrderAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Timestamp dateCreate;
    private Timestamp dateCompletion;
    private Double cost;
    private String status;
    private String client;
    private String mechanic;

}


