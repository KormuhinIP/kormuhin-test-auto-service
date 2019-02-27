package org.vaadin.kormuhin.view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.kormuhin.component.MechanicEditor;
import org.vaadin.kormuhin.model.Mechanic;
import org.vaadin.kormuhin.service.MechanicService;

import javax.annotation.PostConstruct;


@SpringView(name = MechanicView.MECHANIC_VIEW)
public class MechanicView extends VerticalLayout implements View {

    @Autowired
    MechanicEditor mechanicEditor;
    @Autowired
    MechanicService mechanicService;

    public static final String MECHANIC_VIEW = "mechanic";

    @PostConstruct
    public void init() {

        HorizontalLayout hlayout = new HorizontalLayout();
        Grid grid = new Grid(mechanicService.containerMechanic());
        grid.setColumns("lastName", "firstName", "patronymic", "hourlyPay", "orderAutos");
        grid.getColumn("lastName").setHeaderCaption("Фамилия");
        grid.getColumn("firstName").setHeaderCaption("Имя");
        grid.getColumn("patronymic").setHeaderCaption("Очество");
        grid.getColumn("hourlyPay").setHeaderCaption("Почасовая оплата");

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
                mechanicService.deleteMechanic((Mechanic) selection.getSelectedRow());
                grid.getSelectionModel().reset();
                e.getButton().setEnabled(true);
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
            SeriesDefaults seriesDefaults = new SeriesDefaults()
                    .setRenderer(SeriesRenderers.BAR);
            DataSeries dataSeries = new DataSeries().add(2, 6, 7);
            Axes axes = new Axes()
                    .addAxis(
                            new XYaxis()
                                    .setTicks(
                                            new Ticks()
                                                    .add("a", "d", "c")));
            Highlighter highlighter = new Highlighter()
                    .setShow(false);
            Options options = new Options()
                    .setSeriesDefaults(seriesDefaults)
                    .setAxes(axes)
                    .setHighlighter(highlighter);

            DCharts charts = new DCharts()
                    .setDataSeries(dataSeries)
                    .show();
            //  addComponent(charts);
                e.getButton().setEnabled(true);

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