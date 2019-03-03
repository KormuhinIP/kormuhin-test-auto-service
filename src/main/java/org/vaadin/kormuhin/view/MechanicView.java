package org.vaadin.kormuhin.view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.vaadin.kormuhin.component.MechanicEditor;
import org.vaadin.kormuhin.component.MechanicStatistic;
import org.vaadin.kormuhin.domain.Mechanic;
import org.vaadin.kormuhin.service.MechanicService;

import javax.annotation.PostConstruct;


@SpringView(name = MechanicView.MECHANIC_VIEW)
public class MechanicView extends VerticalLayout implements View {

    @Autowired
    MechanicEditor mechanicEditor;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    MechanicStatistic mechanicStatistic;

    public static final String MECHANIC_VIEW = "mechanic";

    @PostConstruct
    public void init() {

        HorizontalLayout hlayout = new HorizontalLayout();
        Grid grid = new Grid(mechanicService.containerMechanic());
        grid.setColumns("lastName", "firstName", "patronymic", "hourlyPay");
        grid.getColumn("lastName").setHeaderCaption("Фамилия");
        grid.getColumn("firstName").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Очество");
        grid.getColumn("hourlyPay").setHeaderCaption("Почасовая оплата");
        grid.setHeight("500px");
        grid.setWidth("700px");

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
                try {
                    mechanicService.deleteMechanic((Mechanic) selection.getSelectedRow());
                    grid.getContainerDataSource().removeItem(selection.getSelectedRow());
                    grid.getSelectionModel().reset();
                    e.getButton().setEnabled(true);
                } catch (DataIntegrityViolationException error) {

                    Notification.show("Нельзя удалить механиков, у которых есть заказы. Удалите в начале заказ.", Notification.Type.TRAY_NOTIFICATION);
                }
            }
        });

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
                mechanicStatistic.StatisticForm(grid, (Mechanic) selection.getSelectedRow());
                e.getButton().setEnabled(true);
            }
        });

        hlayout.addComponents(addMechanic);
        hlayout.addComponents(editMechanic);
        hlayout.addComponents(delSelected);
        hlayout.addComponents(statisticOrder);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}