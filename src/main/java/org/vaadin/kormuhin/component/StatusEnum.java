package org.vaadin.kormuhin.component;


public enum StatusEnum {

    SCHEDULED("Запланирован"),
    TAKEN("Принят клиентом"),
    DONE("Выполнен");


    private String title = null;

    StatusEnum(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

}

