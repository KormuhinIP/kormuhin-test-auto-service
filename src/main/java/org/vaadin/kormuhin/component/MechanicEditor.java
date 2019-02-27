package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.model.Mechanic;
import org.vaadin.kormuhin.service.MechanicService;

import java.util.Locale;

@UIScope
@Component
public class MechanicEditor {

    //  Mechanic mechanic=new Mechanic();
    @Autowired
    MechanicService mechanicService;


    public void editForm(Grid grid, Mechanic editMechanic) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("300px");
        sub.setWidth("400px");
        sub.setPositionX(150);
        sub.setPositionY(400);


        final FormLayout layout = new FormLayout();
        layout.setMargin(true);

        Label errLabel = new Label();

        TextField lastNameText = new TextField("Фамилия");
        lastNameText.setIcon(FontAwesome.USER);
        lastNameText.setInputPrompt(editMechanic.getLastName());
        lastNameText.setValidationVisible(true);
        StringLengthValidator sv = new StringLengthValidator("Введите Фамилию", 3, 15, true);
        lastNameText.addValidator(sv);
        layout.addComponent(lastNameText);

        TextField firstNameText = new TextField("Имя");
        firstNameText.setIcon(FontAwesome.USER);
        firstNameText.setInputPrompt(editMechanic.getFirstName());
        firstNameText.setValidationVisible(true);
        StringLengthValidator slv = new StringLengthValidator("Введите Имя", 3, 10, true);
        firstNameText.addValidator(slv);
        layout.addComponent(firstNameText);

        TextField patronymicText = new TextField("Очество");
        patronymicText.setIcon(FontAwesome.USER);
        patronymicText.setInputPrompt(editMechanic.getPatronymic());
        patronymicText.setValidationVisible(true);
        StringLengthValidator slev = new StringLengthValidator("Введите Очество", 0, 15, true);
        patronymicText.addValidator(slev);
        layout.addComponent(patronymicText);

        TextField hourlyPayDouble = new TextField("Почасовая оплата");
        hourlyPayDouble.setConverter(new DoubleConverter());

        hourlyPayDouble.setValidationVisible(true);
        DoubleRangeValidator dv = new DoubleRangeValidator("Введите величину почасовой оплаты", 10.0, 1000.0);
        hourlyPayDouble.addValidator(dv);
        layout.addComponent(hourlyPayDouble);


        HorizontalLayout hlayout = new HorizontalLayout();

        Button button = new Button("ОК");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                Boolean failed = false;
                errLabel.setValue("");
                try {
                    lastNameText.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(" - " + e.getMessage());
                    lastNameText.setValidationVisible(true);
                    failed = true;
                }

                try {
                    firstNameText.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    firstNameText.setValidationVisible(true);
                    failed = true;
                }
                try {
                    patronymicText.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    patronymicText.setValidationVisible(true);
                    failed = true;
                }
                try {
                    hourlyPayDouble.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    hourlyPayDouble.setValidationVisible(true);
                    failed = true;
                }
                if (!failed) {
                    editMechanic.setLastName(lastNameText.getValue());
                    editMechanic.setFirstName(firstNameText.getValue());
                    editMechanic.setHourlyPay(Double.parseDouble(hourlyPayDouble.getValue()));
                    editMechanic.setPatronymic(patronymicText.getValue());
                    editMechanic.setOrderAutos(0);
                    mechanicService.saveMechanic(editMechanic);
                    grid.setContainerDataSource(mechanicService.containerMechanic());


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
