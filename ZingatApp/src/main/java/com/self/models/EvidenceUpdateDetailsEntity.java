package com.self.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by akash.p on 8/12/16.
 */
@Entity
@Table(name = "evidence_update_details", schema = "", catalog = "hcc_reportmaster")
public class EvidenceUpdateDetailsEntity {
    private int id;
    private String codeId;
    private String evidence;
    private String documentId;
    private Timestamp evidenceAddDate;
    private String evidenceAddById;
    private Integer clientId;
    private String clientName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code_id")
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    @Basic
    @Column(name = "evidence")
    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Basic
    @Column(name = "document_id")
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "evidence_add_date")
    public Timestamp getEvidenceAddDate() {
        return evidenceAddDate;
    }

    public void setEvidenceAddDate(Timestamp evidenceAddDate) {
        this.evidenceAddDate = evidenceAddDate;
    }

    @Basic
    @Column(name = "evidence_add_by_id")
    public String getEvidenceAddById() {
        return evidenceAddById;
    }

    public void setEvidenceAddById(String evidenceAddById) {
        this.evidenceAddById = evidenceAddById;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvidenceUpdateDetailsEntity that = (EvidenceUpdateDetailsEntity) o;

        if (id != that.id) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (codeId != null ? !codeId.equals(that.codeId) : that.codeId != null) return false;
        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (evidence != null ? !evidence.equals(that.evidence) : that.evidence != null) return false;
        if (evidenceAddById != null ? !evidenceAddById.equals(that.evidenceAddById) : that.evidenceAddById != null)
            return false;
        if (evidenceAddDate != null ? !evidenceAddDate.equals(that.evidenceAddDate) : that.evidenceAddDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (codeId != null ? codeId.hashCode() : 0);
        result = 31 * result + (evidence != null ? evidence.hashCode() : 0);
        result = 31 * result + (documentId != null ? documentId.hashCode() : 0);
        result = 31 * result + (evidenceAddDate != null ? evidenceAddDate.hashCode() : 0);
        result = 31 * result + (evidenceAddById != null ? evidenceAddById.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        return result;
    }
}
