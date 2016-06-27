package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "sort_list_master", schema = "", catalog = "zingat")
public class SortListMasterEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String sortList;
    private String defaultFlag;
    private Integer sequence;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sort_list", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSortList() {
        return sortList;
    }

    public void setSortList(String sortList) {
        this.sortList = sortList;
    }

    @Basic
    @Column(name = "default_flag", nullable = true, insertable = true, updatable = true, length = 1)
    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    @Basic
    @Column(name = "sequence", nullable = true, insertable = true, updatable = true)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortListMasterEntity that = (SortListMasterEntity) o;

        if (id != that.id) return false;
        if (defaultFlag != null ? !defaultFlag.equals(that.defaultFlag) : that.defaultFlag != null) return false;
        if (sequence != null ? !sequence.equals(that.sequence) : that.sequence != null) return false;
        if (sortList != null ? !sortList.equals(that.sortList) : that.sortList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sortList != null ? sortList.hashCode() : 0);
        result = 31 * result + (defaultFlag != null ? defaultFlag.hashCode() : 0);
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        return result;
    }
}
