package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
public class AllocaterTLMapEntityPK implements Serializable {

    public static final long serialVersionUID = 1L;

    private int allocatorId;
    private int tlId;

    @Column(name = "allocator_id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getAllocatorId() {
        return allocatorId;
    }

    public void setAllocatorId(int allocaterId) {
        this.allocatorId = allocaterId;
    }

    @Column(name = "tl_id", nullable = false, insertable = true, updatable = true)
    @Id
    public int getTlId() {
        return tlId;
    }

    public void setTlId(int tlId) {
        this.tlId = tlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocaterTLMapEntityPK that = (AllocaterTLMapEntityPK) o;

        if (allocatorId != that.allocatorId) return false;
        if (tlId != that.tlId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = allocatorId;
        result = 31 * result + tlId;
        return result;
    }
}
