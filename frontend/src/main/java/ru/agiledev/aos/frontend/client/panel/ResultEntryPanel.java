package ru.agiledev.aos.frontend.client.panel;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.agiledev.aos.frontend.shared.SearchResult;

/**
 * Created by Anton Kirillov
 * Date: 8/16/11
 */
public class ResultEntryPanel extends VerticalPanel {
    private HorizontalPanel title = new HorizontalPanel();
    private HorizontalPanel domain = new HorizontalPanel();
    private HorizontalPanel text = new HorizontalPanel();

    private HTML titleHtml = new HTML();
    private HTML domainHtml = new HTML();
    private HTML textHtml = new HTML();



    public ResultEntryPanel() {
        setStyleName("resultEntry");
        titleHtml.setStyleName("searchResultTitle");
        add(title);
        title.add(titleHtml);

        domainHtml.setStyleName("resultDomain");
        add(domain);
        domain.add(domainHtml);

        text.setStyleName("searchResultText");
        add(text);
        text.add(textHtml);
    }

    public ResultEntryPanel(SearchResult result) {
        this();
        setResult(result);
    }

    public void setResult(SearchResult res){
        titleHtml.setHTML("<a href="+res.getLink()+">"+res.getTitle()+"</a>");
        domainHtml.setHTML(res.getDomain());
        textHtml.setHTML(res.getText());
    }

}
