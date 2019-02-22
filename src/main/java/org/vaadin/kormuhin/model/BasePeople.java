package org.vaadin.kormuhin.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BasePeople {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymic;

}
