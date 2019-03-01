package org.vaadin.kormuhin.component;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.domain.OrderAuto;
import org.vaadin.kormuhin.service.ClientService;
import org.vaadin.kormuhin.service.OrderAutoService;

@Component
@Data
public class OrderFilter {
    @Autowired
    ClientService clientService;

    @Autowired
    OrderAutoService orderAutoService;



    BeanItemContainer<OrderAuto> container;


    public VerticalLayout orderFilterLayout() {

        container = orderAutoService.containerOrder();

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);


        TextField descriptionText = new TextField("Фильтр");
        descriptionText.setInputPrompt("Описание");
        descriptionText.focus();


        final ComboBox clientSelect = new ComboBox("", clientService.containerClient());
        clientSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        clientSelect.setItemCaptionPropertyId("lastName");
        clientSelect.setNullSelectionAllowed(true);
        clientSelect.setInputPrompt("Клиенты");


        final ComboBox statusSelect = new ComboBox("");
        for (StatusEnum value : StatusEnum.values()) {
            statusSelect.addItem(value.getTitle());
        }
        statusSelect.setNullSelectionAllowed(true);
        statusSelect.setInputPrompt("Статус");


        Button buttonEnter = new Button("Применить");
        buttonEnter.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                Object status = statusSelect.getValue();
                Container.Filter filterStatus = new Compare.Equal("statusOrder", status);

                Object client = clientSelect.getValue();
                Container.Filter filterClient = new Compare.Equal("client", client);

                String description = descriptionText.getValue();
                SimpleStringFilter filterDescription = new SimpleStringFilter("description", description, true, false);

                container.removeAllContainerFilters();
                if (status != null) container.addContainerFilter(filterStatus);
                if (client != null) container.addContainerFilter(filterClient);
                if (description != null) container.addContainerFilter(filterDescription);
            }
        });

        horizontalLayout.addComponent(descriptionText);
        horizontalLayout.addComponent(clientSelect);
        horizontalLayout.addComponent(statusSelect);
        horizontalLayout.addComponent(buttonEnter);
        horizontalLayout.setComponentAlignment(buttonEnter, Alignment.BOTTOM_LEFT);
        final Label label = new Label("");
        horizontalLayout.addComponent(label);
        horizontalLayout.setComponentAlignment(label, Alignment.BOTTOM_LEFT);
        verticalLayout.addComponent(horizontalLayout);
        return verticalLayout;
    }


}
