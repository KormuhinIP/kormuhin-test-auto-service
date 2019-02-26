package org.vaadin.kormuhin.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@Theme("valo")
@SpringUI
@EnableVaadinNavigation
@SpringViewDisplay
class MyUI extends UI implements ViewDisplay {


    private static final String MECHANIC_VIEW = "Механики";
    private static final String CLIENT_VIEW = "Клиенты";
    private static final String ORDER_VIEW = "Заказы";
    private Panel springViewDisplay;


    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton(MECHANIC_VIEW, MechanicView.MECHANIC_VIEW));
        navigationBar.addComponent(createNavigationButton(CLIENT_VIEW, ClientView.CLIENT_VIEW));
        navigationBar.addComponent(createNavigationButton(ORDER_VIEW, OrderView.ORDER_VIEW));
        root.addComponent(navigationBar);
        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}