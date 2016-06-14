package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "rejection_reason_list", schema = "", catalog = "zingat")
public class RejectionReasonListEntity {
    private int rejectionReasonListId;
    private String rejectionReasonList;

    @Id
    @Column(name = "rejection_reason_list_id", nullable = false, insertable = true, updatable = true)
    public int getRejectionReasonListId() {
        return rejectionReasonListId;
    }

    public void setRejectionReasonListId(int rejectionReasonListId) {
        this.rejectionReasonListId = rejectionReasonListId;
    }

    @Basic
    @Column(name = "rejection_reason_list", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getRejectionReasonList() {
        return rejectionReasonList;
    }

    public void setRejectionReasonList(String rejectionReasonList) {
        this.rejectionReasonList = rejectionReasonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RejectionReasonListEntity that = (RejectionReasonListEntity) o;

        if (rejectionReasonListId != that.rejectionReasonListId) return false;
        if (rejectionReasonList != null ? !rejectionReasonList.equals(that.rejectionReasonList) : that.rejectionReasonList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rejectionReasonListId;
        result = 31 * result + (rejectionReasonList != null ? rejectionReasonList.hashCode() : 0);
        return result;
    }
}
