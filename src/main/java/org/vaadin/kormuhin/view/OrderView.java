package org.vaadin.kormuhin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = OrderView.ODER_VIEW)
public class OrderView extends VerticalLayout implements View {
    public static final String ODER_VIEW = "oder";


    @PostConstruct
    void init() {
        addComponent(new Label("This oder."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}
