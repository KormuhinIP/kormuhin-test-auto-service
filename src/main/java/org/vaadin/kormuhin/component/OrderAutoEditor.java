package org.vaadin.kormuhin.component;


import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.model.OrderAuto;
import org.vaadin.kormuhin.service.MechanicService;
import org.vaadin.kormuhin.service.OrderAutoService;

import java.util.Locale;


@Component
public class OrderAutoEditor {

    //  Mechanic mechanic=new Mechanic();
    @Autowired
    OrderAutoService orderAutoService;
    @Autowired
    MechanicService mechanicService;


    public void editForm(Grid grid, OrderAuto editOrder) {

        Window sub = new Window("Изменить/добавить");
        sub.setHeight("300px");
        sub.setWidth("400px");
        sub.setPositionX(150);
        sub.setPositionY(400);


        final FormLayout layout = new FormLayout();
        layout.setMargin(true);

        Label errLabel = new Label();

        TextField descriptionText = new TextField("Описание");
        descriptionText.setInputPrompt(editOrder.getDescription());
        descriptionText.setValidationVisible(true);
        StringLengthValidator sv = new StringLengthValidator("Опишите проблему", 5, 150, true);
        descriptionText.addValidator(sv);
        layout.addComponent(descriptionText);

        DateField createOrder = new DateField("Дата создания заявки");
        //  createOrder.setResolution(Resolution.MINUTE);
        layout.addComponent(createOrder);

        DateField completionOrder = new DateField("Дата окончания работ");
        //  createOrder.setResolution(Resolution.MINUTE);
        layout.addComponent(completionOrder);

        final ComboBox mechanicSelect =
                new ComboBox("Выберите механика", mechanicService.containerMechanic());
        mechanicSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        mechanicSelect.setItemCaptionPropertyId("lastName");
        layout.addComponent(mechanicSelect);


        TextField costDouble = new TextField("Стоимость");
        costDouble.setConverter(new Converter<String, Double>() {
            @Override
            public Double convertToModel(String value,
                                         Class<? extends Double> targetType, Locale locale)
                    throws com.vaadin.data.util.converter.Converter.ConversionException {
                if (value == null)
                    return null;
                if (value.isEmpty()) {
                    return 0.0;
                }
                return Double.parseDouble(value);
            }

            @Override
            public String convertToPresentation(Double value,
                                                Class<? extends String> targetType, Locale locale)
                    throws com.vaadin.data.util.converter.Converter.ConversionException {
                if (value == null)
                    return null;
                return String.valueOf(value);
            }

            @Override
            public Class<Double> getModelType() {
                return Double.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }

        });
        costDouble.setValidationVisible(true);
        DoubleRangeValidator dv = new DoubleRangeValidator("Введите стоимость", 100.0, 100000.0);
        costDouble.addValidator(dv);
        layout.addComponent(costDouble);


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
                    failed = true;
                }

                try {
                    costDouble.validate();
                } catch (Exception e) {
                    errLabel.setValue(errLabel.getValue() + " - " + e.getMessage());
                    costDouble.setValidationVisible(true);
                    failed = true;
                }


                if (!failed) {
                 /*   editOrder.setLastName(lastNameText.getValue());
                    editClient.setFirstName(firstNameText.getValue());
                    editClient.setNumberPhone(Integer.parseInt(phoneNumber.getValue()));
                    editClient.setPatronymic(patronymicText.getValue());*/
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
