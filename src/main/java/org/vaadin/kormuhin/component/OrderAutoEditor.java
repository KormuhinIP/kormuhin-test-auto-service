package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.model.Mechanic;
import org.vaadin.kormuhin.model.OrderAuto;
import org.vaadin.kormuhin.service.ClientService;
import org.vaadin.kormuhin.service.MechanicService;
import org.vaadin.kormuhin.service.OrderAutoService;

import java.util.Locale;


@Component
public class OrderAutoEditor {


    @Autowired
    OrderAutoService orderAutoService;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    ClientService clientService;


    public void editForm(Grid grid, OrderAuto editOrder) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("600px");
        sub.setWidth("600px");
        sub.setPositionX(600);
        sub.setPositionY(200);
        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        Label errLabel = new Label();

        TextField descriptionText = new TextField("Описание");
        descriptionText.setInputPrompt(editOrder.getDescription());
        descriptionText.setValue (editOrder.getDescription()==null?"":editOrder.getDescription());
        descriptionText.setValidationVisible(true);
        StringLengthValidator sv = new StringLengthValidator("Опишите проблему", 5, 150, true);
        descriptionText.addValidator(sv);
        layout.addComponent(descriptionText);

        final ComboBox clientSelect =new ComboBox("Выберите клиента", clientService.containerClient());
        clientSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        clientSelect.setItemCaptionPropertyId("lastName");
        clientSelect.setValue(editOrder.getClient());
        clientSelect.setNullSelectionAllowed(false);
        layout.addComponent(clientSelect);

        final ComboBox mechanicSelect =new ComboBox("Выберите механика", mechanicService.containerMechanic());
        mechanicSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        mechanicSelect.setItemCaptionPropertyId("lastName");
       mechanicSelect.setValue(editOrder.getMechanic());
       mechanicSelect.setNullSelectionAllowed(false);
        layout.addComponent(mechanicSelect);

        DateField createOrder = new DateField("Дата создания заявки");
        createOrder.setResolution(Resolution.MINUTE);
        createOrder.setValue(editOrder.getDateCreate());
        layout.addComponent(createOrder);

        DateField completionOrder = new DateField("Дата окончания работ");
        completionOrder.setResolution(Resolution.MINUTE);
        completionOrder.setValue(editOrder.getDateCompletion());
        layout.addComponent(completionOrder);

        TextField costDouble = new TextField("Стоимость");
        costDouble.setConverter(new DoubleConverter());
        if (editOrder.getCost() != null) {
            costDouble.setValue((String.valueOf(editOrder.getCost())));
            costDouble.setValidationVisible(true);
        DoubleRangeValidator dv = new DoubleRangeValidator("Введите стоимость", 100.0, 100000.0);
        costDouble.addValidator(dv);
            layout.addComponent(costDouble);
        }

        final ComboBox statusSelect =new ComboBox("Выберите статус работы");
        String[] status = {"Запланирован", "Выполнен", "Принят клиентом"};
        statusSelect.addItems(status);
        statusSelect.setNullSelectionAllowed(false);
        statusSelect.setValue(status[0]);
        layout.addComponent(statusSelect);

        HorizontalLayout hlayout = new HorizontalLayout();

        Button button = new Button("ОК");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                Boolean failed = false;
                errLabel.setValue("");
                try {
                    descriptionText.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(" - " + e.getMessage());
                    descriptionText.setValidationVisible(true);
                    //   failed = true;
                }

                try {
                    costDouble.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    costDouble.setValidationVisible(true);
                    // failed = true;
                }


                if (!failed) {
                    editOrder.setDescription(descriptionText.getValue());


                    editOrder.setDateCreate(createOrder.getValue());
                    editOrder.setDateCompletion(completionOrder.getValue());

if (editOrder.getCost()==null){
                    long periodOfHours = (completionOrder.getValue().getTime() - createOrder.getValue().getTime()) / (60 * 60 * 1000);
                    Mechanic mechanic = (Mechanic) mechanicSelect.getValue();
                    System.out.println((Double) mechanic.getHourlyPay());
                    editOrder.setCost(mechanic.getHourlyPay() * periodOfHours);}
else{editOrder.setCost(Double.parseDouble(costDouble.getValue()));}


                    Mechanic mechanic = (Mechanic) mechanicSelect.getValue();
                    int count = mechanic.getOrderAutos() + 1;
                    mechanic.setOrderAutos(count);
                    mechanicService.saveMechanic(mechanic);
                    editOrder.setMechanic(mechanic);
                    editOrder.setMechanicName(mechanic.getLastName());
                    editOrder.setStatusOrder(String.valueOf(statusSelect.getValue()));
                    orderAutoService.saveOrder(editOrder);
                    grid.setContainerDataSource(orderAutoService.containerOrder());


                    Notification.show("Сведения добавлены", Notification.Type.TRAY_NOTIFICATION);
                    sub.close();
                }
            }
        });

        Button buttonClose = new Button("Отменить");
        buttonClose.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
             sub.close();
            }
        });

        hlayout.addComponent(button);
        hlayout.addComponent(buttonClose);
        layout.addComponent(hlayout);
        layout.addComponent(errLabel);
        sub.setContent(layout);
        UI.getCurrent().addWindow(sub);
    }
}
