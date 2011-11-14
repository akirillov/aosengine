package ru.agiledev.wiki.domain.core;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * Created by a.kirillov
 * Date: 10/3/11 Time: 7:00 PM
 */
@Entity
@Table(name = "page")
public class Page {
    private Integer pageId;

    @javax.persistence.Column(name = "page_id")
    @Id
    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    private Integer pageNamespace;

    @javax.persistence.Column(name = "page_namespace")
    @Basic
    public Integer getPageNamespace() {
        return pageNamespace;
    }

    public void setPageNamespace(Integer pageNamespace) {
        this.pageNamespace = pageNamespace;
    }

    private String pageTitle;

    @javax.persistence.Column(name = "page_title")
    @Basic
    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    private byte[] pageRestrictions;

    @javax.persistence.Column(name = "page_restrictions")
    @Basic
    public byte[] getPageRestrictions() {
        return pageRestrictions;
    }

    public void setPageRestrictions(byte[] pageRestrictions) {
        this.pageRestrictions = pageRestrictions;
    }

    private Long pageCounter;

    @javax.persistence.Column(name = "page_counter")
    @Basic
    public Long getPageCounter() {
        return pageCounter;
    }

    public void setPageCounter(Long pageCounter) {
        this.pageCounter = pageCounter;
    }

    private Byte pageIsRedirect;

    @javax.persistence.Column(name = "page_is_redirect")
    @Basic
    public Byte getPageIsRedirect() {
        return pageIsRedirect;
    }

    public void setPageIsRedirect(Byte pageIsRedirect) {
        this.pageIsRedirect = pageIsRedirect;
    }

    private Byte pageIsNew;

    @javax.persistence.Column(name = "page_is_new")
    @Basic
    public Byte getPageIsNew() {
        return pageIsNew;
    }

    public void setPageIsNew(Byte pageIsNew) {
        this.pageIsNew = pageIsNew;
    }

    private Double pageRandom;

    @javax.persistence.Column(name = "page_random")
    @Basic
    public Double getPageRandom() {
        return pageRandom;
    }

    public void setPageRandom(Double pageRandom) {
        this.pageRandom = pageRandom;
    }

    private byte[] pageTouched;

    @javax.persistence.Column(name = "page_touched")
    @Basic
    public byte[] getPageTouched() {
        return pageTouched;
    }

    public void setPageTouched(byte[] pageTouched) {
        this.pageTouched = pageTouched;
    }

    private Integer pageLatest;

    @javax.persistence.Column(name = "page_latest")
    @Basic
    public Integer getPageLatest() {
        return pageLatest;
    }

    public void setPageLatest(Integer pageLatest) {
        this.pageLatest = pageLatest;
    }

    private Integer pageLen;

    @javax.persistence.Column(name = "page_len")
    @Basic
    public Integer getPageLen() {
        return pageLen;
    }

    public void setPageLen(Integer pageLen) {
        this.pageLen = pageLen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (pageCounter != null ? !pageCounter.equals(page.pageCounter) : page.pageCounter != null) return false;
        if (pageId != null ? !pageId.equals(page.pageId) : page.pageId != null) return false;
        if (pageIsNew != null ? !pageIsNew.equals(page.pageIsNew) : page.pageIsNew != null) return false;
        if (pageIsRedirect != null ? !pageIsRedirect.equals(page.pageIsRedirect) : page.pageIsRedirect != null)
            return false;
        if (pageLatest != null ? !pageLatest.equals(page.pageLatest) : page.pageLatest != null) return false;
        if (pageLen != null ? !pageLen.equals(page.pageLen) : page.pageLen != null) return false;
        if (pageNamespace != null ? !pageNamespace.equals(page.pageNamespace) : page.pageNamespace != null)
            return false;
        if (pageRandom != null ? !pageRandom.equals(page.pageRandom) : page.pageRandom != null) return false;
        if (!Arrays.equals(pageRestrictions, page.pageRestrictions)) return false;
        if (pageTitle != null ? !pageTitle.equals(page.pageTitle) : page.pageTitle != null) return false;
        if (!Arrays.equals(pageTouched, page.pageTouched)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageId != null ? pageId.hashCode() : 0;
        result = 31 * result + (pageNamespace != null ? pageNamespace.hashCode() : 0);
        result = 31 * result + (pageTitle != null ? pageTitle.hashCode() : 0);
        result = 31 * result + (pageRestrictions != null ? Arrays.hashCode(pageRestrictions) : 0);
        result = 31 * result + (pageCounter != null ? pageCounter.hashCode() : 0);
        result = 31 * result + (pageIsRedirect != null ? pageIsRedirect.hashCode() : 0);
        result = 31 * result + (pageIsNew != null ? pageIsNew.hashCode() : 0);
        result = 31 * result + (pageRandom != null ? pageRandom.hashCode() : 0);
        result = 31 * result + (pageTouched != null ? Arrays.hashCode(pageTouched) : 0);
        result = 31 * result + (pageLatest != null ? pageLatest.hashCode() : 0);
        result = 31 * result + (pageLen != null ? pageLen.hashCode() : 0);
        return result;
    }
}
