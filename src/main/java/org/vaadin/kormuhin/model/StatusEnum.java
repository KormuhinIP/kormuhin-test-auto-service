package org.vaadin.kormuhin.model;

public enum StatusEnum {
    MERCURY("Mercury"),
    VENUS("Venus"),
    EARTH("Earth");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}