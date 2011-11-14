package ru.agiledev.aos.frontend.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by Anton Kirillov
 * Date: 8/16/11
 */
public class SearchResult implements IsSerializable {
    String title;
    String domain;
    String link;
    String text;

    public SearchResult(){}

    public SearchResult(String title, String link, String text, String domain) {
        this.title = title;
        this.link = link;
        this.text = text;
        this.domain = domain;
    }


    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
