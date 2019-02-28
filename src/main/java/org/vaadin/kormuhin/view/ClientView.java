package org.vaadin.kormuhin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.kormuhin.component.ClientEditor;
import org.vaadin.kormuhin.domain.Client;
import org.vaadin.kormuhin.service.ClientService;

import javax.annotation.PostConstruct;

@SpringView(name = ClientView.CLIENT_VIEW)
public class ClientView extends VerticalLayout implements View {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientEditor clientEditor;


    public static final String CLIENT_VIEW = "client";

    @PostConstruct
    public void init() {

        HorizontalLayout hlayout = new HorizontalLayout();
        Grid grid = new Grid(clientService.containerClient());
        grid.setColumns("lastName", "firstName", "patronymic", "numberPhone");
        grid.getColumn("lastName").setHeaderCaption("Фамилия");
        grid.getColumn("firstName").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Очество");
        grid.getColumn("numberPhone").setHeaderCaption("Номер телефона");

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
                clientService.deleteClient((Client) selection.getSelectedRow());
                grid.getSelectionModel().reset();
                e.getButton().setEnabled(true);
            }
        });

        Button addMechanic = new Button("Добавить", e -> {
            clientEditor.editForm(grid, new Client());
            grid.getSelectionModel().reset();
        });

        Button editMechanic = new Button("Изменить", e -> {
            if (selection.getSelectedRow() != null) {
                clientEditor.editForm(grid, (Client) selection.getSelectedRow());
                grid.getSelectionModel().reset();
            }
        });


        hlayout.addComponents(addMechanic);
        hlayout.addComponents(editMechanic);
        hlayout.addComponents(delSelected);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}