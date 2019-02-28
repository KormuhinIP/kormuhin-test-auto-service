package org.vaadin.kormuhin.component;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.service.OrderAutoService;

@Component
public class MechanicStatistic {
    @Autowired
    OrderAutoService orderAutoService;

    public void StatisticForm(Grid grid, Mechanic mechanic) {

        Window sub = new Window("Статистика");
        sub.setHeight("300px");
        sub.setWidth("600px");
        sub.setPositionX(600);
        sub.setPositionY(200);
        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        Label errLabel = new Label();

        DateField createOrder = new DateField("Период создания заказа, с");
        createOrder.setResolution(Resolution.MINUTE);
        layout.addComponent(createOrder);

        DateField completionOrder = new DateField("Период создания заказа, по");
        completionOrder.setResolution(Resolution.MINUTE);
        layout.addComponent(completionOrder);

        TextField statistic = new TextField("Количество заказов за выбранный период");

        HorizontalLayout hlayout = new HorizontalLayout();

        Button button = new Button("ОК");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                statistic.setValue(String.valueOf(orderAutoService.getStatistic(mechanic, createOrder.getValue(), completionOrder.getValue())));


            }

        });


        Button buttonClose = new Button("Закрыть");
        buttonClose.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                sub.close();


            }
        });


        hlayout.addComponent(button);
        hlayout.addComponent(buttonClose);
        layout.addComponent(hlayout);
        layout.addComponent(statistic);
        layout.addComponent(errLabel);


        sub.setContent(layout);
        UI.getCurrent().addWindow(sub);
    }
}
