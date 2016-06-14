package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "roles_default_settings", schema = "", catalog = "zingat")
public class RolesDefaultSettingsEntity {
    private int id;
    private String roleName;
    private Integer documentAllocation;
    private Integer editDocument;
    private Integer rejectDocument;
    private Integer viewDocument;
    private Integer draftDocument;
    private Integer completeConfirmationMessage;
    private Integer draftConfirmationMessage;
    private Integer continueTheSamebucket;
    private Integer viewReport;
    private String clientName;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "document_allocation", nullable = true, insertable = true, updatable = true)
    public Integer getDocumentAllocation() {
        return documentAllocation;
    }

    public void setDocumentAllocation(Integer documentAllocation) {
        this.documentAllocation = documentAllocation;
    }

    @Basic
    @Column(name = "edit_document", nullable = true, insertable = true, updatable = true)
    public Integer getEditDocument() {
        return editDocument;
    }

    public void setEditDocument(Integer editDocument) {
        this.editDocument = editDocument;
    }

    @Basic
    @Column(name = "reject_document", nullable = true, insertable = true, updatable = true)
    public Integer getRejectDocument() {
        return rejectDocument;
    }

    public void setRejectDocument(Integer rejectDocument) {
        this.rejectDocument = rejectDocument;
    }

    @Basic
    @Column(name = "view_document", nullable = true, insertable = true, updatable = true)
    public Integer getViewDocument() {
        return viewDocument;
    }

    public void setViewDocument(Integer viewDocument) {
        this.viewDocument = viewDocument;
    }

    @Basic
    @Column(name = "draft_document", nullable = true, insertable = true, updatable = true)
    public Integer getDraftDocument() {
        return draftDocument;
    }

    public void setDraftDocument(Integer draftDocument) {
        this.draftDocument = draftDocument;
    }

    @Basic
    @Column(name = "complete_confirmation_message", nullable = true, insertable = true, updatable = true)
    public Integer getCompleteConfirmationMessage() {
        return completeConfirmationMessage;
    }

    public void setCompleteConfirmationMessage(Integer completeConfirmationMessage) {
        this.completeConfirmationMessage = completeConfirmationMessage;
    }

    @Basic
    @Column(name = "draft_confirmation_message", nullable = true, insertable = true, updatable = true)
    public Integer getDraftConfirmationMessage() {
        return draftConfirmationMessage;
    }

    public void setDraftConfirmationMessage(Integer draftConfirmationMessage) {
        this.draftConfirmationMessage = draftConfirmationMessage;
    }

    @Basic
    @Column(name = "continue_the_samebucket", nullable = true, insertable = true, updatable = true)
    public Integer getContinueTheSamebucket() {
        return continueTheSamebucket;
    }

    public void setContinueTheSamebucket(Integer continueTheSamebucket) {
        this.continueTheSamebucket = continueTheSamebucket;
    }

    @Basic
    @Column(name = "view_report", nullable = true, insertable = true, updatable = true)
    public Integer getViewReport() {
        return viewReport;
    }

    public void setViewReport(Integer viewReport) {
        this.viewReport = viewReport;
    }

    @Basic
    @Column(name = "client_name", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesDefaultSettingsEntity that = (RolesDefaultSettingsEntity) o;

        if (id != that.id) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (completeConfirmationMessage != null ? !completeConfirmationMessage.equals(that.completeConfirmationMessage) : that.completeConfirmationMessage != null)
            return false;
        if (continueTheSamebucket != null ? !continueTheSamebucket.equals(that.continueTheSamebucket) : that.continueTheSamebucket != null)
            return false;
        if (documentAllocation != null ? !documentAllocation.equals(that.documentAllocation) : that.documentAllocation != null)
            return false;
        if (draftConfirmationMessage != null ? !draftConfirmationMessage.equals(that.draftConfirmationMessage) : that.draftConfirmationMessage != null)
            return false;
        if (draftDocument != null ? !draftDocument.equals(that.draftDocument) : that.draftDocument != null)
            return false;
        if (editDocument != null ? !editDocument.equals(that.editDocument) : that.editDocument != null) return false;
        if (rejectDocument != null ? !rejectDocument.equals(that.rejectDocument) : that.rejectDocument != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (viewDocument != null ? !viewDocument.equals(that.viewDocument) : that.viewDocument != null) return false;
        if (viewReport != null ? !viewReport.equals(that.viewReport) : that.viewReport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (documentAllocation != null ? documentAllocation.hashCode() : 0);
        result = 31 * result + (editDocument != null ? editDocument.hashCode() : 0);
        result = 31 * result + (rejectDocument != null ? rejectDocument.hashCode() : 0);
        result = 31 * result + (viewDocument != null ? viewDocument.hashCode() : 0);
        result = 31 * result + (draftDocument != null ? draftDocument.hashCode() : 0);
        result = 31 * result + (completeConfirmationMessage != null ? completeConfirmationMessage.hashCode() : 0);
        result = 31 * result + (draftConfirmationMessage != null ? draftConfirmationMessage.hashCode() : 0);
        result = 31 * result + (continueTheSamebucket != null ? continueTheSamebucket.hashCode() : 0);
        result = 31 * result + (viewReport != null ? viewReport.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        return result;
    }
}
