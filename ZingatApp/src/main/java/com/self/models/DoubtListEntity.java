package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 2/12/16.
 */
@Entity
@Table(name = "doubt_list", schema = "", catalog = "hcc_reportmaster")
public class DoubtListEntity {
    private int doubtId;
    private String doubtDisplay;
    private String doubtDesc;

    @Id
    @Column(name = "doubt_id")
    public int getDoubtId() {
        return doubtId;
    }

    public void setDoubtId(int doubtId) {
        this.doubtId = doubtId;
    }

    @Basic
    @Column(name = "doubt_display")
    public String getDoubtDisplay() {
        return doubtDisplay;
    }

    public void setDoubtDisplay(String doubtDisplay) {
        this.doubtDisplay = doubtDisplay;
    }

    @Basic
    @Column(name = "doubt_desc")
    public String getDoubtDesc() {
        return doubtDesc;
    }

    public void setDoubtDesc(String doubtDesc) {
        this.doubtDesc = doubtDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubtListEntity that = (DoubtListEntity) o;

        if (doubtId != that.doubtId) return false;
        if (doubtDesc != null ? !doubtDesc.equals(that.doubtDesc) : that.doubtDesc != null) return false;
        if (doubtDisplay != null ? !doubtDisplay.equals(that.doubtDisplay) : that.doubtDisplay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doubtId;
        result = 31 * result + (doubtDisplay != null ? doubtDisplay.hashCode() : 0);
        result = 31 * result + (doubtDesc != null ? doubtDesc.hashCode() : 0);
        return result;
    }
}
