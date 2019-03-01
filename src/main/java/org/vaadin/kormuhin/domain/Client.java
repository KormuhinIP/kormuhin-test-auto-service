package org.vaadin.kormuhin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;


@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Client extends BasePeople {

    private String numberPhone;

}


