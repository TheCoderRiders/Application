package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 28/11/16.
 */
public class AllocatorAuditorMapEntityPK implements Serializable {
    private int allocatorId;
    private int auditorId;

    @Column(name = "Allocator_id")
    @Id
    public int getAllocatorId() {
        return allocatorId;
    }

    public void setAllocatorId(int allocatorId) {
        this.allocatorId = allocatorId;
    }

    @Column(name = "auditor_id")
    @Id
    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocatorAuditorMapEntityPK that = (AllocatorAuditorMapEntityPK) o;

        if (allocatorId != that.allocatorId) return false;
        if (auditorId != that.auditorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = allocatorId;
        result = 31 * result + auditorId;
        return result;
    }
}
