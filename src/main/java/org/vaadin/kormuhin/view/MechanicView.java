package org.vaadin.kormuhin.view;


import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.kormuhin.component.MechanicEditor;
import org.vaadin.kormuhin.model.Mechanic;
import org.vaadin.kormuhin.repository.MechanicRepository;
import org.vaadin.kormuhin.service.MechanicService;

@Component
@UIScope
public class MechanicView {

    @Autowired
    MechanicEditor mechanicEditor;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    MechanicRepository repository;

    public void MechanicTable(VerticalLayout vlayout) {


        HorizontalLayout hlayout = new HorizontalLayout();
        Grid grid = new Grid(mechanicService.containerMechanic());
        grid.setColumns("lastName", "firstName", "patronymic", "hourlyPay");
        grid.getColumn("lastName").setHeaderCaption("Фамилия");
        grid.getColumn("firstName").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Очество");
        grid.getColumn("hourlyPay").setHeaderCaption("Почасовая оплата");

        vlayout.addComponents(grid);
        hlayout.setSpacing(true);
        vlayout.addComponent(hlayout);
        vlayout.setMargin(true);
        vlayout.setSpacing(true);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        Grid.SingleSelectionModel selection =
                (Grid.SingleSelectionModel) grid.getSelectionModel();


        Button delSelected = new Button("Удалить", e -> {
            if (selection.getSelectedRow() != null) {
                grid.getContainerDataSource().removeItem(selection.getSelectedRow());
                mechanicService.deleteMechanic((Mechanic) selection.getSelectedRow());
                grid.getSelectionModel().reset();
                e.getButton().setEnabled(true);
            }
        });

        // Grid finalGrid=grid;
        Button addMechanic = new Button("Добавить", e -> {
            mechanicEditor.editForm(grid, new Mechanic());
            grid.getSelectionModel().reset();
        });

        Button editMechanic = new Button("Изменить", e -> {
            if (selection.getSelectedRow() != null) {
                mechanicEditor.editForm(grid, (Mechanic) selection.getSelectedRow());
                grid.getSelectionModel().reset();
            }
        });

        Button statisticOrder = new Button("Количество заказов", e -> {
            if (selection.getSelectedRow() != null) {
                Mechanic mechanic = (Mechanic) selection.getSelectedRow();
                mechanic.getNumberOrders();
                e.getButton().setEnabled(true);
            }
        });

        hlayout.addComponents(addMechanic);
        hlayout.addComponents(editMechanic);
        hlayout.addComponents(delSelected);
        hlayout.addComponents(statisticOrder);


    }

}