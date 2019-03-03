package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.service.MechanicService;

@UIScope
@Component
public class MechanicEditor {

    @Autowired
    MechanicService mechanicService;


    public void editForm(Grid grid, Mechanic editMechanic) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("360px");
        sub.setWidth("400px");
        sub.setPositionX(150);
        sub.setPositionY(400);


        final FormLayout layout = new FormLayout();
        layout.setMargin(true);

        Label errLabel = new Label();

        TextField lastNameText = new TextField("Фамилия");
        lastNameText.setIcon(FontAwesome.USER);
        lastNameText.setValue(editMechanic.getLastName() == null ? "" : editMechanic.getLastName());
        lastNameText.setValidationVisible(true);
        lastNameText.addValidator(new StringLengthValidator("Введите Фамилию", 3, 20, true));
        layout.addComponent(lastNameText);

        TextField firstNameText = new TextField("Имя");
        firstNameText.setIcon(FontAwesome.USER);
        firstNameText.setValue(editMechanic.getFirstName() == null ? "" : editMechanic.getFirstName());
        firstNameText.setValidationVisible(true);
        firstNameText.addValidator(new StringLengthValidator("Введите Имя", 3, 15, true));
        layout.addComponent(firstNameText);

        TextField patronymicText = new TextField("Очество");
        patronymicText.setIcon(FontAwesome.USER);
        patronymicText.setValue(editMechanic.getPatronymic() == null ? "" : editMechanic.getPatronymic());
        patronymicText.setValidationVisible(true);
        patronymicText.addValidator(new StringLengthValidator("Введите Очество", 0, 15, true));
        layout.addComponent(patronymicText);

        TextField hourlyPayDouble = new TextField("Почасовая оплата");
        hourlyPayDouble.setConverter(new DoubleConverter());
        hourlyPayDouble.setValue(editMechanic.getHourlyPay() == 0.0 ? "" : String.valueOf(editMechanic.getHourlyPay()));
        hourlyPayDouble.setValidationVisible(true);
        hourlyPayDouble.addValidator(new DoubleRangeValidator("Введите величину почасовой оплаты", 10.0, 1000.0));
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
                    errLabel.setValue(e.getMessage());
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
        hlayout.setSpacing(true);
        hlayout.addComponent(button);
        hlayout.addComponent(buttonClose);
        layout.addComponent(hlayout);
        layout.addComponent(errLabel);
        sub.setContent(layout);
        UI.getCurrent().addWindow(sub);


    }
}
