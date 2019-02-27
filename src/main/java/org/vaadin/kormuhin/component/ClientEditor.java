package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.model.Client;
import org.vaadin.kormuhin.service.ClientService;


@Component
public class ClientEditor {

    @Autowired
    ClientService clientService;


    public void editForm(Grid grid, Client editClient) {

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

        lastNameText.setValue(editClient.getLastName() == null ? "" : editClient.getLastName());
        lastNameText.setValidationVisible(true);
        StringLengthValidator sv = new StringLengthValidator("Введите Фамилию", 3, 15, true);
        lastNameText.addValidator(sv);
        layout.addComponent(lastNameText);

        TextField firstNameText = new TextField("Имя");
        firstNameText.setIcon(FontAwesome.USER);
        firstNameText.setValue(editClient.getFirstName() == null ? "" : editClient.getFirstName());
        firstNameText.setValidationVisible(true);
        StringLengthValidator slv = new StringLengthValidator("Введите Имя", 3, 10, true);
        firstNameText.addValidator(slv);
        layout.addComponent(firstNameText);

        TextField patronymicText = new TextField("Очество");
        patronymicText.setIcon(FontAwesome.USER);
        patronymicText.setValue(editClient.getPatronymic() == null ? "" : editClient.getPatronymic());
        patronymicText.setValidationVisible(true);
        StringLengthValidator slev = new StringLengthValidator("Введите Очество", 0, 15, true);
        patronymicText.addValidator(slev);
        layout.addComponent(patronymicText);


        TextField phoneNumber = new TextField("Номер телефона");

        patronymicText.setInputPrompt(editClient.getPatronymic());
        phoneNumber.setValidationVisible(true);
        phoneNumber.setValue(editClient.getNumberPhone() == null ? "" : String.valueOf(editClient.getNumberPhone()));
        IntegerRangeValidator iv = new IntegerRangeValidator("Введите номер телефона", 0, 1000000);
        phoneNumber.addValidator(iv);
        layout.addComponent(phoneNumber);


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
                    phoneNumber.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    phoneNumber.setValidationVisible(true);
                    // failed = true;
                }
                if (!failed) {
                    editClient.setLastName(lastNameText.getValue());
                    editClient.setFirstName(firstNameText.getValue());
                    editClient.setNumberPhone(Integer.parseInt(phoneNumber.getValue()));
                    editClient.setPatronymic(patronymicText.getValue());
                    clientService.saveClient(editClient);
                    grid.setContainerDataSource(clientService.containerClient());


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
