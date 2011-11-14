package ru.agiledev.aos.frontend.client.panel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Created by Anton Kirillov
 * Date: 8/18/11
 */
public class NavigationPanel extends HorizontalPanel {
    FocusPanel akb = new FocusPanel();
    FocusPanel description = new FocusPanel();
    HorizontalPanel mail = new HorizontalPanel();


    public NavigationPanel() {
        description.setStyleName("navyDiv");
        description.add(new HTML("О проекте"));

        akb.setStyleName("navyDiv");
        akb.add(new HTML("АОБЗ"));

        mail.add(new HTML("<a href=\"mailto:aspectsearch.adm@gmail.com\"><img src=\"img/mail-icon.png\" scale=\"80%\"></a>"));
        mail.setStyleName("navyDivMail");
        /*<a href="http://xml.yandex.ru/" target="_blank">Яндекс.XML</a>*/

        add(description);
        add(akb);
        add(mail);

        description.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Window.Location.assign("http://code.google.com/p/aosengine/");
            }
        });

        akb.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Window.Location.assign("http://aosengine.ru/akb/");
            }
        });
    }
}
