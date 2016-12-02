package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 2/12/16.
 */
@Entity
@Table(name = "rebuttal_list", schema = "", catalog = "hcc_reportmaster")
public class RebuttalListEntity {
    private int rebuttalId;
    private String rebuttalDisplay;
    private String rebuttalDesc;

    @Id
    @Column(name = "rebuttal_id")
    public int getRebuttalId() {
        return rebuttalId;
    }

    public void setRebuttalId(int rebuttalId) {
        this.rebuttalId = rebuttalId;
    }

    @Basic
    @Column(name = "rebuttal_display")
    public String getRebuttalDisplay() {
        return rebuttalDisplay;
    }

    public void setRebuttalDisplay(String rebuttalDisplay) {
        this.rebuttalDisplay = rebuttalDisplay;
    }

    @Basic
    @Column(name = "rebuttal_desc")
    public String getRebuttalDesc() {
        return rebuttalDesc;
    }

    public void setRebuttalDesc(String rebuttalDesc) {
        this.rebuttalDesc = rebuttalDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RebuttalListEntity that = (RebuttalListEntity) o;

        if (rebuttalId != that.rebuttalId) return false;
        if (rebuttalDesc != null ? !rebuttalDesc.equals(that.rebuttalDesc) : that.rebuttalDesc != null) return false;
        if (rebuttalDisplay != null ? !rebuttalDisplay.equals(that.rebuttalDisplay) : that.rebuttalDisplay != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rebuttalId;
        result = 31 * result + (rebuttalDisplay != null ? rebuttalDisplay.hashCode() : 0);
        result = 31 * result + (rebuttalDesc != null ? rebuttalDesc.hashCode() : 0);
        return result;
    }
}
