package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 28/11/16.
 */
public class AuditorCoderMapEntityPK implements Serializable {
    private int auditorId;
    private int coderId;

    @Column(name = "auditor_id")
    @Id
    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    @Column(name = "coder_id")
    @Id
    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditorCoderMapEntityPK that = (AuditorCoderMapEntityPK) o;

        if (auditorId != that.auditorId) return false;
        if (coderId != that.coderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auditorId;
        result = 31 * result + coderId;
        return result;
    }
}
