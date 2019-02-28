package org.vaadin.kormuhin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.kormuhin.component.ClientConverter;
import org.vaadin.kormuhin.component.MechanicConverter;
import org.vaadin.kormuhin.component.OrderAutoEditor;
import org.vaadin.kormuhin.component.OrderFilter;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.domain.OrderAuto;
import org.vaadin.kormuhin.service.MechanicService;
import org.vaadin.kormuhin.service.OrderAutoService;

import javax.annotation.PostConstruct;

@SpringView(name = OrderView.ORDER_VIEW)
public class OrderView extends VerticalLayout implements View {
    public static final String ORDER_VIEW = "";

    @Autowired
    OrderAutoEditor orderAutoEditor;
    @Autowired
    OrderAutoService orderAutoService;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    OrderFilter orderFilter;

    @PostConstruct
    public void initOrder() {

        HorizontalLayout hlayout = new HorizontalLayout();
        addComponent(orderFilter.orderFilterLayout());
        Grid grid = new Grid(orderFilter.getContainer());

        grid.setColumns("description", "client", "mechanic", "dateCreate", "dateCompletion", "cost", "statusOrder");
        grid.getColumn("description").setHeaderCaption("Описание");
        grid.getColumn("client").setHeaderCaption("Клиент").setConverter(new ClientConverter());
        grid.getColumn("mechanic").setHeaderCaption("Механик").setConverter(new MechanicConverter());
        grid.getColumn("dateCreate").setHeaderCaption("Дата создания").setRenderer(new DateRenderer("%1$td-%1$tm-%1$tY/ %1$tH.%1$tM"));
        grid.getColumn("dateCompletion").setHeaderCaption("Дата окончания работ").setRenderer(new DateRenderer("%1$td-%1$tm-%1$tY/ %1$tH.%1$tM"));
        grid.getColumn("cost").setHeaderCaption("Стоимость");
        grid.getColumn("statusOrder").setHeaderCaption("Статус");

        grid.setHeight("500px");
        grid.setWidth("1500px");

        addComponents(grid);
        hlayout.setSpacing(true);
        addComponent(hlayout);
        setMargin(true);
        setSpacing(true);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        Grid.SingleSelectionModel selection =
                (Grid.SingleSelectionModel) grid.getSelectionModel();


        Button delSelected = new Button("Удалить", e -> {
            if (selection.getSelectedRow() != null) {
                grid.getContainerDataSource().removeItem(selection.getSelectedRow());
                orderAutoService.deleteOrder((OrderAuto) selection.getSelectedRow());
                Mechanic mechanic=((OrderAuto) selection.getSelectedRow()).getMechanic();
                mechanic.setOrderAutos(mechanic.getOrderAutos()-1);
               mechanicService.saveMechanic(mechanic);
                grid.getSelectionModel().reset();
                e.getButton().setEnabled(true);
            }
        });

        Button addOrderAuto = new Button("Добавить", e -> {
            orderAutoEditor.editForm(grid, new OrderAuto());
            grid.getSelectionModel().reset();
        });

        Button editOrderAuto = new Button("Изменить", e -> {
            if (selection.getSelectedRow() != null) {
                orderAutoEditor.editForm(grid, (OrderAuto) selection.getSelectedRow());
                grid.getSelectionModel().reset();
            }
        });

        hlayout.addComponents(addOrderAuto);
        hlayout.addComponents(editOrderAuto);
        hlayout.addComponents(delSelected);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
