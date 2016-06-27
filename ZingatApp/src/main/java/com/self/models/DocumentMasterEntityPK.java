package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
public class DocumentMasterEntityPK implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String documentId;;

    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "document_id", nullable = false, insertable = true, updatable = true)
    @Id
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentMasterEntityPK that = (DocumentMasterEntityPK) o;

        if (documentId != that.documentId) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + documentId.hashCode();
        return result;
    }
}
