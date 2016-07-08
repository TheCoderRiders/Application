package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "status_master", schema = "", catalog = "hcc_reportmaster")
public class StatusMasterEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int statusId;
    private String statusValue;
    private String statusDescription;

    @Id
    @Column(name = "status_id", nullable = false, insertable = true, updatable = true)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "status_value", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Basic
    @Column(name = "status_description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusMasterEntity that = (StatusMasterEntity) o;

        if (statusId != that.statusId) return false;
        if (statusDescription != null ? !statusDescription.equals(that.statusDescription) : that.statusDescription != null)
            return false;
        if (statusValue != null ? !statusValue.equals(that.statusValue) : that.statusValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (statusValue != null ? statusValue.hashCode() : 0);
        result = 31 * result + (statusDescription != null ? statusDescription.hashCode() : 0);
        return result;
    }
}
