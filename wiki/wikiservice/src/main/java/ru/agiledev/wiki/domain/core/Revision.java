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
@Table(name = "revision")
public class Revision {
    private Integer revId;

    @javax.persistence.Column(name = "rev_id")
    @Id
    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    private Integer revPage;

    @javax.persistence.Column(name = "rev_page")
    @Basic
    public Integer getRevPage() {
        return revPage;
    }

    public void setRevPage(Integer revPage) {
        this.revPage = revPage;
    }

    private Integer revTextId;

    @javax.persistence.Column(name = "rev_text_id")
    @Basic
    public Integer getRevTextId() {
        return revTextId;
    }

    public void setRevTextId(Integer revTextId) {
        this.revTextId = revTextId;
    }

    private byte[] revComment;

    @javax.persistence.Column(name = "rev_comment")
    @Basic
    public byte[] getRevComment() {
        return revComment;
    }

    public void setRevComment(byte[] revComment) {
        this.revComment = revComment;
    }

    private Integer revUser;

    @javax.persistence.Column(name = "rev_user")
    @Basic
    public Integer getRevUser() {
        return revUser;
    }

    public void setRevUser(Integer revUser) {
        this.revUser = revUser;
    }

    private String revUserText;

    @javax.persistence.Column(name = "rev_user_text")
    @Basic
    public String getRevUserText() {
        return revUserText;
    }

    public void setRevUserText(String revUserText) {
        this.revUserText = revUserText;
    }

    private byte[] revTimestamp;

    @javax.persistence.Column(name = "rev_timestamp")
    @Basic
    public byte[] getRevTimestamp() {
        return revTimestamp;
    }

    public void setRevTimestamp(byte[] revTimestamp) {
        this.revTimestamp = revTimestamp;
    }

    private Byte revMinorEdit;

    @javax.persistence.Column(name = "rev_minor_edit")
    @Basic
    public Byte getRevMinorEdit() {
        return revMinorEdit;
    }

    public void setRevMinorEdit(Byte revMinorEdit) {
        this.revMinorEdit = revMinorEdit;
    }

    private Byte revDeleted;

    @javax.persistence.Column(name = "rev_deleted")
    @Basic
    public Byte getRevDeleted() {
        return revDeleted;
    }

    public void setRevDeleted(Byte revDeleted) {
        this.revDeleted = revDeleted;
    }

    private Integer revLen;

    @javax.persistence.Column(name = "rev_len")
    @Basic
    public Integer getRevLen() {
        return revLen;
    }

    public void setRevLen(Integer revLen) {
        this.revLen = revLen;
    }

    private Integer revParentId;

    @javax.persistence.Column(name = "rev_parent_id")
    @Basic
    public Integer getRevParentId() {
        return revParentId;
    }

    public void setRevParentId(Integer revParentId) {
        this.revParentId = revParentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revision revision = (Revision) o;

        if (!Arrays.equals(revComment, revision.revComment)) return false;
        if (revDeleted != null ? !revDeleted.equals(revision.revDeleted) : revision.revDeleted != null) return false;
        if (revId != null ? !revId.equals(revision.revId) : revision.revId != null) return false;
        if (revLen != null ? !revLen.equals(revision.revLen) : revision.revLen != null) return false;
        if (revMinorEdit != null ? !revMinorEdit.equals(revision.revMinorEdit) : revision.revMinorEdit != null)
            return false;
        if (revPage != null ? !revPage.equals(revision.revPage) : revision.revPage != null) return false;
        if (revParentId != null ? !revParentId.equals(revision.revParentId) : revision.revParentId != null)
            return false;
        if (revTextId != null ? !revTextId.equals(revision.revTextId) : revision.revTextId != null) return false;
        if (!Arrays.equals(revTimestamp, revision.revTimestamp)) return false;
        if (revUser != null ? !revUser.equals(revision.revUser) : revision.revUser != null) return false;
        if (revUserText != null ? !revUserText.equals(revision.revUserText) : revision.revUserText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = revId != null ? revId.hashCode() : 0;
        result = 31 * result + (revPage != null ? revPage.hashCode() : 0);
        result = 31 * result + (revTextId != null ? revTextId.hashCode() : 0);
        result = 31 * result + (revComment != null ? Arrays.hashCode(revComment) : 0);
        result = 31 * result + (revUser != null ? revUser.hashCode() : 0);
        result = 31 * result + (revUserText != null ? revUserText.hashCode() : 0);
        result = 31 * result + (revTimestamp != null ? Arrays.hashCode(revTimestamp) : 0);
        result = 31 * result + (revMinorEdit != null ? revMinorEdit.hashCode() : 0);
        result = 31 * result + (revDeleted != null ? revDeleted.hashCode() : 0);
        result = 31 * result + (revLen != null ? revLen.hashCode() : 0);
        result = 31 * result + (revParentId != null ? revParentId.hashCode() : 0);
        return result;
    }
}
