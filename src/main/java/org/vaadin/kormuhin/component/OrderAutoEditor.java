package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.Client;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.domain.OrderAuto;
import org.vaadin.kormuhin.service.ClientService;
import org.vaadin.kormuhin.service.MechanicService;
import org.vaadin.kormuhin.service.OrderAutoService;



@Component
public class OrderAutoEditor {

    @Autowired
    OrderAutoService orderAutoService;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    ClientService clientService;
    @Autowired
    OrderFilter orderFilter;

    public void editForm(Grid grid, OrderAuto editOrder) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("500px");
        sub.setWidth("550px");
        sub.setPositionX(600);
        sub.setPositionY(150);
        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        Label errLabel = new Label();

        TextField descriptionText = new TextField("Описание");
        descriptionText.setValue (editOrder.getDescription()==null?"":editOrder.getDescription());
        descriptionText.setValidationVisible(true);
        descriptionText.addValidator(new StringLengthValidator("Опишите проблему", 5, 150, true));
        layout.addComponent(descriptionText);

        final ComboBox clientSelect =new ComboBox("Выберите клиента", clientService.containerClient());
        clientSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        clientSelect.setItemCaptionPropertyId("lastName");
        clientSelect.setValue(editOrder.getClient());
        clientSelect.addValidator(new NullValidator("Выберите клиента", false));
        clientSelect.setNullSelectionAllowed(false);
        layout.addComponent(clientSelect);

        final ComboBox mechanicSelect =new ComboBox("Выберите механика", mechanicService.containerMechanic());
        mechanicSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        mechanicSelect.setItemCaptionPropertyId("lastName");
        mechanicSelect.setValue(editOrder.getMechanic());
        mechanicSelect.addValidator(new NullValidator("Выберите механика", false));
        mechanicSelect.setNullSelectionAllowed(false);
        layout.addComponent(mechanicSelect);

        DateField createOrder = new DateField("Дата создания заявки");
        createOrder.setResolution(Resolution.MINUTE);
        createOrder.addValidator(new NullValidator("Укажите дату создания заявки", false));
        createOrder.setValue(editOrder.getDateCreate());
        layout.addComponent(createOrder);

        DateField completionOrder = new DateField("Дата окончания работ");
        completionOrder.setResolution(Resolution.MINUTE);
        completionOrder.setValue(editOrder.getDateCompletion());
        completionOrder.addValidator(new NullValidator("Укажите дату окончания работ", false));
        layout.addComponent(completionOrder);

        TextField costDouble = new TextField("Стоимость");
        costDouble.setConverter(new DoubleConverter());
        if (editOrder.getCost() != null) {
            costDouble.setValue((String.valueOf(editOrder.getCost())));
            costDouble.setValidationVisible(true);
            costDouble.addValidator(new DoubleRangeValidator("Введите стоимость", 100.0, 9999999.0));
            layout.addComponent(costDouble);
        }

        final ComboBox statusSelect =new ComboBox("Выберите статус работы");
        for (StatusEnum value : StatusEnum.values()) {
            statusSelect.addItem(value.getTitle());
        }
        statusSelect.setNullSelectionAllowed(false);
        statusSelect.setValue(editOrder.getStatusOrder());
        statusSelect.addValidator(new NullValidator("Выберите статус", false));
        layout.addComponent(statusSelect);

        HorizontalLayout hlayout = new HorizontalLayout();

        Button button = new Button("ОК");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                Boolean failed = false;
                errLabel.setValue("");
                //validation

                try {
                    descriptionText.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(e.getMessage());
                    descriptionText.setValidationVisible(true);
                    failed = true;
                }

                try {
                    clientSelect.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    clientSelect.setValidationVisible(true);
                    failed = true;
                }

                try {
                    mechanicSelect.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    mechanicSelect.setValidationVisible(true);
                    failed = true;
                }

                try {
                    createOrder.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    createOrder.setValidationVisible(true);
                    failed = true;
                }

                try {
                    completionOrder.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    completionOrder.setValidationVisible(true);
                    failed = true;
                }

                try {
                    costDouble.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    costDouble.setValidationVisible(true);
                    failed = true;
                }

                try {
                    statusSelect.validate();
                } catch (Validator.InvalidValueException e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    statusSelect.setValidationVisible(true);
                    failed = true;
                }

                if (completionOrder.getValue() != null && createOrder.getValue() != null && completionOrder.getValue().getTime() < createOrder.getValue().getTime()) {
                    failed = true;
                    errLabel.setValue(errLabel.getValue() + " - Дата создания позднее даты окончания, исправьте");
                }

                if (!failed) {

                    Mechanic mechanic = (Mechanic) mechanicSelect.getValue();                                    //set the price
if (editOrder.getCost()==null){
                    long periodOfHours = (completionOrder.getValue().getTime() - createOrder.getValue().getTime()) / (60 * 60 * 1000);
                    editOrder.setCost(mechanic.getHourlyPay() * periodOfHours);}
else{editOrder.setCost(Double.parseDouble(costDouble.getValue()));}

                    editOrder.setMechanic(mechanic);
                    editOrder.setClient((Client) clientSelect.getValue());
                    editOrder.setDescription(descriptionText.getValue());
                    editOrder.setDateCreate(createOrder.getValue());
                    editOrder.setDateCompletion(completionOrder.getValue());
                    editOrder.setStatusOrder(String.valueOf(statusSelect.getValue()));
                    orderAutoService.saveOrder(editOrder);

                    orderFilter.orderFilterLayout();                                  //update container in OrderFilter
                    grid.setContainerDataSource(orderFilter.getContainer());           //transfer the updated container to the grid (to filter when adding a new record to orders)

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
