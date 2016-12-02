package com.self.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by akash.p on 2/12/16.
 */
@Entity
@Table(name = "acknowledgement_details", schema = "", catalog = "hcc_reportmaster")
public class AcknowledgementDetailsEntity {
    private int acknowledgementId;
    private String documentAssignedId;
    private String documentAssignedName;
    private String documentAssigneeId;
    private String documentAssigneeName;
    private Integer clientId;
    private String clientName;
    private String commentDisplay;
    private String commentText;
    private String commentStatus;
    private Timestamp commentDate;

    @Id
    @Column(name = "acknowledgement_id")
    public int getAcknowledgementId() {
        return acknowledgementId;
    }

    public void setAcknowledgementId(int acknowledgementId) {
        this.acknowledgementId = acknowledgementId;
    }

    @Basic
    @Column(name = "document_assigned_id")
    public String getDocumentAssignedId() {
        return documentAssignedId;
    }

    public void setDocumentAssignedId(String documentAssignedId) {
        this.documentAssignedId = documentAssignedId;
    }

    @Basic
    @Column(name = "document_assigned_name")
    public String getDocumentAssignedName() {
        return documentAssignedName;
    }

    public void setDocumentAssignedName(String documentAssignedName) {
        this.documentAssignedName = documentAssignedName;
    }

    @Basic
    @Column(name = "document_assignee_id")
    public String getDocumentAssigneeId() {
        return documentAssigneeId;
    }

    public void setDocumentAssigneeId(String documentAssigneeId) {
        this.documentAssigneeId = documentAssigneeId;
    }

    @Basic
    @Column(name = "document_assignee_name")
    public String getDocumentAssigneeName() {
        return documentAssigneeName;
    }

    public void setDocumentAssigneeName(String documentAssigneeName) {
        this.documentAssigneeName = documentAssigneeName;
    }

    @Basic
    @Column(name = "client_id")
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "comment_display")
    public String getCommentDisplay() {
        return commentDisplay;
    }

    public void setCommentDisplay(String commentDisplay) {
        this.commentDisplay = commentDisplay;
    }

    @Basic
    @Column(name = "comment_text")
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Basic
    @Column(name = "comment_status")
    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    @Basic
    @Column(name = "comment_date")
    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcknowledgementDetailsEntity that = (AcknowledgementDetailsEntity) o;

        if (acknowledgementId != that.acknowledgementId) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        if (commentDisplay != null ? !commentDisplay.equals(that.commentDisplay) : that.commentDisplay != null)
            return false;
        if (commentStatus != null ? !commentStatus.equals(that.commentStatus) : that.commentStatus != null)
            return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;
        if (documentAssignedId != null ? !documentAssignedId.equals(that.documentAssignedId) : that.documentAssignedId != null)
            return false;
        if (documentAssignedName != null ? !documentAssignedName.equals(that.documentAssignedName) : that.documentAssignedName != null)
            return false;
        if (documentAssigneeId != null ? !documentAssigneeId.equals(that.documentAssigneeId) : that.documentAssigneeId != null)
            return false;
        if (documentAssigneeName != null ? !documentAssigneeName.equals(that.documentAssigneeName) : that.documentAssigneeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = acknowledgementId;
        result = 31 * result + (documentAssignedId != null ? documentAssignedId.hashCode() : 0);
        result = 31 * result + (documentAssignedName != null ? documentAssignedName.hashCode() : 0);
        result = 31 * result + (documentAssigneeId != null ? documentAssigneeId.hashCode() : 0);
        result = 31 * result + (documentAssigneeName != null ? documentAssigneeName.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (commentDisplay != null ? commentDisplay.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (commentStatus != null ? commentStatus.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        return result;
    }
}
