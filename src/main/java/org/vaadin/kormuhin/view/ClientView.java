package org.vaadin.kormuhin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = ClientView.CLIENT_VIEW)
public class ClientView extends VerticalLayout implements View {
    public static final String CLIENT_VIEW = "client";

    @PostConstruct
    void init() {
        addComponent(new Label("This is a view scoped view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}