package ru.agiledev.aos.frontend.client.panel;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Created by Anton Kirillov
 * Date: 8/10/11
 */
public class FooterPanel extends HorizontalPanel {

    public FooterPanel() {

        add(new HTML("<center>Научный проект создан при поддержке <a href=\"http://www.hse.ru\" target=\"_blank\">НИУ-ВШЭ<center>"));
        setStyleName("footer");
    }
}
