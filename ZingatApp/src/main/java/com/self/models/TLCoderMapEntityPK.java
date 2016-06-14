package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
public class TLCoderMapEntityPK implements Serializable {
    private int tlId;
    private int coderId;

    @Column(name = "tl_id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getTlId() {
        return tlId;
    }

    public void setTlId(int tlId) {
        this.tlId = tlId;
    }

    @Column(name = "coder_id", nullable = false, insertable = true, updatable = true)
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

        TLCoderMapEntityPK that = (TLCoderMapEntityPK) o;

        if (coderId != that.coderId) return false;
        if (tlId != that.tlId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tlId;
        result = 31 * result + coderId;
        return result;
    }
}
