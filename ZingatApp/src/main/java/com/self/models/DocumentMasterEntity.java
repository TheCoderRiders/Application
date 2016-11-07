package com.self.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@javax.persistence.Table(name = "document_master", schema = "", catalog = "hcc_reportmaster")
@IdClass(DocumentMasterEntityPK.class)
public class DocumentMasterEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;

    @Id
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String documentId;

    @Id
    @javax.persistence.Column(name = "document_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    private String documentName;

    @Basic
    @javax.persistence.Column(name = "document_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    private String documentPath;

    @Basic
    @javax.persistence.Column(name = "document_path", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    private String documentRejectionComment;

    @Basic
    @javax.persistence.Column(name = "document_rejection_comment", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDocumentRejectionComment() {
        return documentRejectionComment;
    }

    public void setDocumentRejectionComment(String documentRejectionComment) {
        this.documentRejectionComment = documentRejectionComment;
    }

    private String documentRejectionReason;

    @Basic
    @javax.persistence.Column(name = "document_rejection_reason", nullable = true, insertable = true, updatable = true, length = 50)
    public String getDocumentRejectionReason() {
        return documentRejectionReason;
    }

    public void setDocumentRejectionReason(String documentRejectionReason) {
        this.documentRejectionReason = documentRejectionReason;
    }

    private String documentCurrentStatus;

    @Basic
    @javax.persistence.Column(name = "document_current_status", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentCurrentStatus() {
        return documentCurrentStatus;
    }

    public void setDocumentCurrentStatus(String documentCurrentStatus) {
        this.documentCurrentStatus = documentCurrentStatus;
    }

    private int documentCurrentStatusId;

    @Basic
    @javax.persistence.Column(name = "document_current_status_id", nullable = false, insertable = true, updatable = true)
    public int getDocumentCurrentStatusId() {
        return documentCurrentStatusId;
    }

    public void setDocumentCurrentStatusId(int documentCurrentStatusId) {
        this.documentCurrentStatusId = documentCurrentStatusId;
    }

    private Timestamp documentRecivedDatetime;

    @Basic
    @javax.persistence.Column(name = "document_recived_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDocumentRecivedDatetime() {
        return documentRecivedDatetime;
    }

    public void setDocumentRecivedDatetime(Timestamp documentRecivedDatetime) {
        this.documentRecivedDatetime = documentRecivedDatetime;
    }

    private Timestamp documentParsedDatetime;

    @Basic
    @javax.persistence.Column(name = "document_parsed_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDocumentParsedDatetime() {
        return documentParsedDatetime;
    }

    public void setDocumentParsedDatetime(Timestamp documentParsedDatetime) {
        this.documentParsedDatetime = documentParsedDatetime;
    }

    private Timestamp documentAssignedDatetime;

    @Basic
    @javax.persistence.Column(name = "document_assigned_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDocumentAssignedDatetime() {
        return documentAssignedDatetime;
    }

    public void setDocumentAssignedDatetime(Timestamp documentAssignedDatetime) {
        this.documentAssignedDatetime = documentAssignedDatetime;
    }

    private Timestamp documentStartDatetime;

    @Basic
    @javax.persistence.Column(name = "document_start_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDocumentStartDatetime() {
        return documentStartDatetime;
    }

    public void setDocumentStartDatetime(Timestamp documentStartDatetime) {
        this.documentStartDatetime = documentStartDatetime;
    }

    private Timestamp documentEndDatetime;

    @Basic
    @javax.persistence.Column(name = "document_end_datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDocumentEndDatetime() {
        return documentEndDatetime;
    }

    public void setDocumentEndDatetime(Timestamp documentEndDatetime) {
        this.documentEndDatetime = documentEndDatetime;
    }

    //private String documentContents;

    /*@Basic
    @javax.persistence.Column(name = "document_html_contents", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getDocumentContents() {
        return documentContents;
    }

    public void setDocumentContents(String documentContents) {
        this.documentContents = documentContents;
    }*/

    private String documentSuggestedCodes;

    @Basic
    @javax.persistence.Column(name = "document_suggested_codes", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDocumentSuggestedCodes() {
        return documentSuggestedCodes;
    }

    public void setDocumentSuggestedCodes(String documentSuggestedCodes) {
        this.documentSuggestedCodes = documentSuggestedCodes;
    }

    private String documentAcceptedCodes;

    @Basic
    @javax.persistence.Column(name = "document_accepted_codes", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDocumentAcceptedCodes() {
        return documentAcceptedCodes;
    }

    public void setDocumentAcceptedCodes(String documentAcceptedCodes) {
        this.documentAcceptedCodes = documentAcceptedCodes;
    }

    private String documentRejectedCodes;

    @Basic
    @javax.persistence.Column(name = "document_rejected_codes", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDocumentRejectedCodes() {
        return documentRejectedCodes;
    }

    public void setDocumentRejectedCodes(String documentRejectedCodes) {
        this.documentRejectedCodes = documentRejectedCodes;
    }

    private String documentMayBeCodes;

    @Basic
    @javax.persistence.Column(name = "document_may_be_codes", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDocumentMayBeCodes() {
        return documentMayBeCodes;
    }

    public void setDocumentMayBeCodes(String documentMayBeCodes) {
        this.documentMayBeCodes = documentMayBeCodes;
    }

    private String documentLocked;

    @Basic
    @javax.persistence.Column(name = "document_locked", nullable = true, insertable = true, updatable = true, length = 1)
    public String getDocumentLocked() {
        return documentLocked;
    }

    public void setDocumentLocked(String documentLocked) {
        this.documentLocked = documentLocked;
    }

    private String documentLockedBy;

    @Basic
    @javax.persistence.Column(name = "document_locked_by", nullable = true, insertable = true, updatable = true, length = 50)
    public String getDocumentLockedBy() {
        return documentLockedBy;
    }

    public void setDocumentLockedBy(String documentLockedBy) {
        this.documentLockedBy = documentLockedBy;
    }

    private Integer documentLockedById;

    @Basic
    @javax.persistence.Column(name = "document_locked_by_id", nullable = true, insertable = true, updatable = true)
    public Integer getDocumentLockedById() {
        return documentLockedById;
    }

    public void setDocumentLockedById(Integer documentLockedById) {
        this.documentLockedById = documentLockedById;
    }

    private String documentAssignedId;

    @Basic
    @javax.persistence.Column(name = "document_assigned_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentAssignedId() {
        return documentAssignedId;
    }

    public void setDocumentAssignedId(String documentAssignedId) {
        this.documentAssignedId = documentAssignedId;
    }

    private String documentAssignedName;

    @Basic
    @javax.persistence.Column(name = "document_assigned_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentAssignedName() {
        return documentAssignedName;
    }

    public void setDocumentAssignedName(String documentAssignedName) {
        this.documentAssignedName = documentAssignedName;
    }

    private String documentAssigneeId;

    @Basic
    @javax.persistence.Column(name = "document_assignee_id", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentAssigneeId() {
        return documentAssigneeId;
    }

    public void setDocumentAssigneeId(String documentAssigneeId) {
        this.documentAssigneeId = documentAssigneeId;
    }

    private String documentAssigneeName;

    @Basic
    @javax.persistence.Column(name = "document_assignee_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getDocumentAssigneeName() {
        return documentAssigneeName;
    }

    public void setDocumentAssigneeName(String documentAssigneeName) {
        this.documentAssigneeName = documentAssigneeName;
    }

    private Integer clientId;

    @Basic
    @javax.persistence.Column(name = "client_id", nullable = true, insertable = true, updatable = true)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    private String clientName;

    @Basic
    @javax.persistence.Column(name = "client_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private String physicianName;

    @Basic
    @javax.persistence.Column(name = "physician_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    private String reportType;

    @Basic
    @javax.persistence.Column(name = "report_type", nullable = true, insertable = true, updatable = true, length = 50)
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    private String codeSuggestedFlag;

    @Basic
    @javax.persistence.Column(name = "code_suggested_flag", nullable = true, insertable = true, updatable = true, length = 1)
    public String getCodeSuggestedFlag() {
        return codeSuggestedFlag;
    }

    public void setCodeSuggestedFlag(String codeSuggestedFlag) {
        this.codeSuggestedFlag = codeSuggestedFlag;
    }

    private String updatedBy;

    @Basic
    @javax.persistence.Column(name = "updated_by", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private Integer updatedById;

    @Basic
    @javax.persistence.Column(name = "updated_by_id", nullable = true, insertable = true, updatable = true)
    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    private Timestamp updatedDate;

    @Basic
    @javax.persistence.Column(name = "updated_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    private Timestamp codeSuggestedDate;

    @Basic
    @javax.persistence.Column(name = "code_suggested_date")
    public Timestamp getCodeSuggestedDate() {
        return codeSuggestedDate;
    }

    public void setCodeSuggestedDate(Timestamp codeSuggestedDate) {
        this.codeSuggestedDate = codeSuggestedDate;
    }
    private Timestamp documentRejectedDatetime;

    @Basic
    @javax.persistence.Column(name = "document_rejected_datetime")
    public Timestamp getDocumentRejectedDatetime() {
        return documentRejectedDatetime;
    }

    public void setDocumentRejectedDatetime(Timestamp documentRejectedDatetime) {
        this.documentRejectedDatetime = documentRejectedDatetime;
    }

    //private String documentSectionContents;

   /* @Basic
    @javax.persistence.Column(name = "document_section_contents")
    public String getDocumentSectionContents() {
        return documentSectionContents;
    }

    public void setDocumentSectionContents(String documentSectionContents) {
        this.documentSectionContents = documentSectionContents;
    }*/

    private String documentFilePath;

    @Basic
    @javax.persistence.Column(name = "document_file_path")
    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentMasterEntity that = (DocumentMasterEntity) o;

        if (documentCurrentStatusId != that.documentCurrentStatusId) return false;
        if (id != that.id) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (codeSuggestedFlag != null ? !codeSuggestedFlag.equals(that.codeSuggestedFlag) : that.codeSuggestedFlag != null)
            return false;
        if (documentAcceptedCodes != null ? !documentAcceptedCodes.equals(that.documentAcceptedCodes) : that.documentAcceptedCodes != null)
            return false;
        if (documentAssignedDatetime != null ? !documentAssignedDatetime.equals(that.documentAssignedDatetime) : that.documentAssignedDatetime != null)
            return false;
        if (documentAssignedId != null ? !documentAssignedId.equals(that.documentAssignedId) : that.documentAssignedId != null)
            return false;
        if (documentAssignedName != null ? !documentAssignedName.equals(that.documentAssignedName) : that.documentAssignedName != null)
            return false;
        if (documentAssigneeId != null ? !documentAssigneeId.equals(that.documentAssigneeId) : that.documentAssigneeId != null)
            return false;
        if (documentAssigneeName != null ? !documentAssigneeName.equals(that.documentAssigneeName) : that.documentAssigneeName != null)
            return false;
        /*if (documentContents != null ? !documentContents.equals(that.documentContents) : that.documentContents != null)
            return false;*/
        if (documentCurrentStatus != null ? !documentCurrentStatus.equals(that.documentCurrentStatus) : that.documentCurrentStatus != null)
            return false;
        if (documentEndDatetime != null ? !documentEndDatetime.equals(that.documentEndDatetime) : that.documentEndDatetime != null)
            return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (documentLocked != null ? !documentLocked.equals(that.documentLocked) : that.documentLocked != null)
            return false;
        if (documentLockedBy != null ? !documentLockedBy.equals(that.documentLockedBy) : that.documentLockedBy != null)
            return false;
        if (documentLockedById != null ? !documentLockedById.equals(that.documentLockedById) : that.documentLockedById != null)
            return false;
        if (documentName != null ? !documentName.equals(that.documentName) : that.documentName != null) return false;
        if (documentParsedDatetime != null ? !documentParsedDatetime.equals(that.documentParsedDatetime) : that.documentParsedDatetime != null)
            return false;
        if (documentPath != null ? !documentPath.equals(that.documentPath) : that.documentPath != null) return false;
        if (documentRecivedDatetime != null ? !documentRecivedDatetime.equals(that.documentRecivedDatetime) : that.documentRecivedDatetime != null)
            return false;
        if (documentRejectedCodes != null ? !documentRejectedCodes.equals(that.documentRejectedCodes) : that.documentRejectedCodes != null)
            return false;
        if (documentMayBeCodes != null ? !documentMayBeCodes.equals(that.documentMayBeCodes) : that.documentMayBeCodes != null)
            return false;
        if (documentRejectionComment != null ? !documentRejectionComment.equals(that.documentRejectionComment) : that.documentRejectionComment != null)
            return false;
        if (documentRejectionReason != null ? !documentRejectionReason.equals(that.documentRejectionReason) : that.documentRejectionReason != null)
            return false;
        if (documentStartDatetime != null ? !documentStartDatetime.equals(that.documentStartDatetime) : that.documentStartDatetime != null)
            return false;
        if (documentSuggestedCodes != null ? !documentSuggestedCodes.equals(that.documentSuggestedCodes) : that.documentSuggestedCodes != null)
            return false;
        if (physicianName != null ? !physicianName.equals(that.physicianName) : that.physicianName != null)
            return false;
        if (reportType != null ? !reportType.equals(that.reportType) : that.reportType != null) return false;
        if (updatedBy != null ? !updatedBy.equals(that.updatedBy) : that.updatedBy != null) return false;
        if (updatedById != null ? !updatedById.equals(that.updatedById) : that.updatedById != null) return false;
        if (updatedDate != null ? !updatedDate.equals(that.updatedDate) : that.updatedDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (documentName != null ? documentName.hashCode() : 0);
        result = 31 * result + (documentPath != null ? documentPath.hashCode() : 0);
        result = 31 * result + (documentRejectionComment != null ? documentRejectionComment.hashCode() : 0);
        result = 31 * result + (documentRejectionReason != null ? documentRejectionReason.hashCode() : 0);
        result = 31 * result + (documentCurrentStatus != null ? documentCurrentStatus.hashCode() : 0);
        result = 31 * result + documentCurrentStatusId;
        result = 31 * result + (documentRecivedDatetime != null ? documentRecivedDatetime.hashCode() : 0);
        result = 31 * result + (documentParsedDatetime != null ? documentParsedDatetime.hashCode() : 0);
        result = 31 * result + (documentAssignedDatetime != null ? documentAssignedDatetime.hashCode() : 0);
        result = 31 * result + (documentStartDatetime != null ? documentStartDatetime.hashCode() : 0);
        result = 31 * result + (documentEndDatetime != null ? documentEndDatetime.hashCode() : 0);
//        result = 31 * result + (documentContents != null ? documentContents.hashCode() : 0);
        result = 31 * result + (documentSuggestedCodes != null ? documentSuggestedCodes.hashCode() : 0);
        result = 31 * result + (documentAcceptedCodes != null ? documentAcceptedCodes.hashCode() : 0);
        result = 31 * result + (documentRejectedCodes != null ? documentRejectedCodes.hashCode() : 0);
        result = 31 * result + (documentMayBeCodes != null ? documentMayBeCodes.hashCode() : 0);
        result = 31 * result + (documentLocked != null ? documentLocked.hashCode() : 0);
        result = 31 * result + (documentLockedBy != null ? documentLockedBy.hashCode() : 0);
        result = 31 * result + (documentLockedById != null ? documentLockedById.hashCode() : 0);
        result = 31 * result + (documentAssignedId != null ? documentAssignedId.hashCode() : 0);
        result = 31 * result + (documentAssignedName != null ? documentAssignedName.hashCode() : 0);
        result = 31 * result + (documentAssigneeId != null ? documentAssigneeId.hashCode() : 0);
        result = 31 * result + (documentAssigneeName != null ? documentAssigneeName.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (physicianName != null ? physicianName.hashCode() : 0);
        result = 31 * result + (reportType != null ? reportType.hashCode() : 0);
        result = 31 * result + (codeSuggestedFlag != null ? codeSuggestedFlag.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (updatedById != null ? updatedById.hashCode() : 0);
        result = 31 * result + (updatedDate != null ? updatedDate.hashCode() : 0);
        return result;
    }
}
