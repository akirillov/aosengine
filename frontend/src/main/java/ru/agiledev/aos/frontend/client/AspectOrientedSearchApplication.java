package ru.agiledev.aos.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.agiledev.aos.frontend.client.panel.FooterPanel;
import ru.agiledev.aos.frontend.client.panel.NavigationPanel;
import ru.agiledev.aos.frontend.client.panel.QueryPanel;
import ru.agiledev.aos.frontend.client.panel.ResultPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AspectOrientedSearchApplication implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    private static AspectOrientedSearchApplication threadLocal;
    private QueryPanel queryPanel;
    private ResultPanel resultsPanel;
    private FooterPanel footer;
    private VerticalPanel mainPanel;
    private NavigationPanel navyPanel;
    //private HeaderPanel header = new HeaderPanel();todo:implement

    public static AspectOrientedSearchApplication get(){
        return threadLocal;
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {


        threadLocal = this;//todo: add normal event-based approach (on session start)


        mainPanel = new VerticalPanel();
        mainPanel.setStyleName("mainPanel");

        navyPanel  = new NavigationPanel();
        queryPanel = new QueryPanel();
        resultsPanel = new ResultPanel();
        footer = new FooterPanel();


        final Label errorLabel = new Label();

        RootPanel.get("mainPanel").add(mainPanel);
        Image logo = new Image("img/logo.jpg");
        logo.setStyleName("logo");

        mainPanel.add(logo);
        mainPanel.add(queryPanel);
        //mainPanel.add(resultsPanel);
        RootPanel.get("header").add(navyPanel);
        RootPanel.get("footer").add(footer);

        RootPanel.get("errorLabelContainer").add(errorLabel);
    }

    public ResultPanel getResultPanel(){
        return resultsPanel;
    }

    public void updateForSearchResults(){
        mainPanel.insert(resultsPanel,2);
        mainPanel.setStyleName("mainPanelFilled");
    }
}
