package org.vaadin.kormuhin.view;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
class MyUI extends UI {


    @Autowired
    MechanicView mechanicView;




    protected void init(VaadinRequest request) {
        VerticalLayout vcontent = new VerticalLayout();
        HorizontalLayout hcontent = new HorizontalLayout();


        mechanicView.MechanicTable(vcontent);
        setContent(vcontent);

        //  content.setMargin(true);
        //  content.addComponent(new Label("Hello!"));


    }
}
