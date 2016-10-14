package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 14/10/16.
 */
@Entity
@Table(name = "doc_rejection_reason_list", schema = "", catalog = "hcc_reportmaster")
public class DocRejectionReasonListEntity {
    private int rejectionReasonListId;
    private String rejectionReasonDisplay;
    private String rejectionReasonDesc;

    @Id
    @Column(name = "rejection_reason_list_id")
    public int getRejectionReasonListId() {
        return rejectionReasonListId;
    }

    public void setRejectionReasonListId(int rejectionReasonListId) {
        this.rejectionReasonListId = rejectionReasonListId;
    }

    @Basic
    @Column(name = "rejection_reason_display")
    public String getRejectionReasonDisplay() {
        return rejectionReasonDisplay;
    }

    public void setRejectionReasonDisplay(String rejectionReasonDisplay) {
        this.rejectionReasonDisplay = rejectionReasonDisplay;
    }

    @Basic
    @Column(name = "rejection_reason_desc")
    public String getRejectionReasonDesc() {
        return rejectionReasonDesc;
    }

    public void setRejectionReasonDesc(String rejectionReasonDesc) {
        this.rejectionReasonDesc = rejectionReasonDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocRejectionReasonListEntity that = (DocRejectionReasonListEntity) o;

        if (rejectionReasonListId != that.rejectionReasonListId) return false;
        if (rejectionReasonDesc != null ? !rejectionReasonDesc.equals(that.rejectionReasonDesc) : that.rejectionReasonDesc != null)
            return false;
        if (rejectionReasonDisplay != null ? !rejectionReasonDisplay.equals(that.rejectionReasonDisplay) : that.rejectionReasonDisplay != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rejectionReasonListId;
        result = 31 * result + (rejectionReasonDisplay != null ? rejectionReasonDisplay.hashCode() : 0);
        result = 31 * result + (rejectionReasonDesc != null ? rejectionReasonDesc.hashCode() : 0);
        return result;
    }
}
