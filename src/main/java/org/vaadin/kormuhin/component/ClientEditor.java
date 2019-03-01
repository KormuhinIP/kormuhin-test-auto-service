package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.Client;
import org.vaadin.kormuhin.service.ClientService;


@Component
public class ClientEditor {

    @Autowired
    ClientService clientService;


    public void editForm(Grid grid, Client editClient) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("380px");
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
        lastNameText.addValidator(new StringLengthValidator("Введите Фамилию", 3, 20, true));
        layout.addComponent(lastNameText);

        TextField firstNameText = new TextField("Имя");
        firstNameText.setIcon(FontAwesome.USER);
        firstNameText.setValue(editClient.getFirstName() == null ? "" : editClient.getFirstName());
        firstNameText.setValidationVisible(true);
        firstNameText.addValidator(new StringLengthValidator("Введите Имя", 3, 10, true));
        layout.addComponent(firstNameText);

        TextField patronymicText = new TextField("Очество");
        patronymicText.setIcon(FontAwesome.USER);
        patronymicText.setValue(editClient.getPatronymic() == null ? "" : editClient.getPatronymic());
        patronymicText.setValidationVisible(true);
        patronymicText.addValidator(new StringLengthValidator("Введите Очество", 0, 25, true));
        layout.addComponent(patronymicText);


        TextField phoneNumber = new TextField("Номер телефона");

        patronymicText.setInputPrompt(editClient.getPatronymic());
        phoneNumber.setValidationVisible(true);
        phoneNumber.setValue(editClient.getNumberPhone() == null ? "" : String.valueOf(editClient.getNumberPhone()));
        phoneNumber.setInputPrompt("+7-917-818-0299");
        phoneNumber.addValidator(new RegexpValidator("^^(\\+)([0-9]-[0-9]{3}-[0-9]{3}-[0-9]{4})", "Введите номер телефона"));
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
                    phoneNumber.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    phoneNumber.setValidationVisible(true);
                    failed = true;
                }
                if (!failed) {
                    editClient.setLastName(lastNameText.getValue());
                    editClient.setFirstName(firstNameText.getValue());
                    editClient.setNumberPhone(phoneNumber.getValue());
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
        hlayout.setSpacing(true);
        hlayout.addComponent(button);
        hlayout.addComponent(buttonClose);
        layout.addComponent(hlayout);
        layout.addComponent(errLabel);

        sub.setContent(layout);
        UI.getCurrent().addWindow(sub);


    }
}
