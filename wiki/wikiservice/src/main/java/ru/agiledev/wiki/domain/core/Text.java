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
@Table(name = "text")
public class Text {
    private Integer oldId;

    @javax.persistence.Column(name = "old_id")
    @Id
    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    private String oldText;

    @javax.persistence.Column(name = "old_text")
    @Basic
    public String getOldText() {
        return oldText;
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
    }

    private byte[] oldFlags;

    @javax.persistence.Column(name = "old_flags")
    @Basic
    public byte[] getOldFlags() {
        return oldFlags;
    }

    public void setOldFlags(byte[] oldFlags) {
        this.oldFlags = oldFlags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        if (!Arrays.equals(oldFlags, text.oldFlags)) return false;
        if (oldId != null ? !oldId.equals(text.oldId) : text.oldId != null) return false;
        if (oldText != null ? !oldText.equals(text.oldText) : text.oldText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oldId != null ? oldId.hashCode() : 0;
        result = 31 * result + (oldText != null ? oldText.hashCode() : 0);
        result = 31 * result + (oldFlags != null ? Arrays.hashCode(oldFlags) : 0);
        return result;
    }
}
