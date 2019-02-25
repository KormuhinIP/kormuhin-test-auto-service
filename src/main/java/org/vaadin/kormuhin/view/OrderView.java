package org.vaadin.kormuhin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.kormuhin.component.OrderAutoEditor;
import org.vaadin.kormuhin.model.OrderAuto;
import org.vaadin.kormuhin.service.OrderAutoService;

import javax.annotation.PostConstruct;


@SpringView(name = OrderView.ORDER_VIEW)
public class OrderView extends VerticalLayout implements View {
    public static final String ORDER_VIEW = "";

    @Autowired
    OrderAutoEditor orderAutoEditor;
    @Autowired
    OrderAutoService orderAutoService;

    @PostConstruct
    public void initOrder() {

        HorizontalLayout hlayout = new HorizontalLayout();
        Grid grid = new Grid(orderAutoService.containerOrder());
        grid.setColumns("description", "client", "mechanic", "dateCreate", "dateCompletion", "cost", "status");
        grid.getColumn("description").setHeaderCaption("Описание");
        grid.getColumn("client").setHeaderCaption("Клиент");
        grid.getColumn("mechanic").setHeaderCaption("Механик");
        grid.getColumn("dateCreate").setHeaderCaption("Дата создания");
        grid.getColumn("dateCompletion").setHeaderCaption("Дата окончания работ");
        grid.getColumn("cost").setHeaderCaption("Стоимость");
        grid.getColumn("status").setHeaderCaption("Статус");

        grid.setHeight("500px");
        grid.setWidth("1000px");
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
