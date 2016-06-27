package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "allocater_tl_map", schema = "", catalog = "zingat")
@IdClass(AllocaterTLMapEntityPK.class)
public class AllocaterTLMapEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int allocaterId;
    private String allocaterFirstname;
    private String allocaterMiddlename;
    private String allocaterLastname;
    private int tlId;
    private String tlFirstname;
    private String tlMiddlename;
    private String tlLastname;
    private String allocaterClientName;
    private String tlClientName;

    @Id
    @Column(name = "allocater_id", nullable = false, insertable = true, updatable = true)
    public int getAllocaterId() {
        return allocaterId;
    }

    public void setAllocaterId(int allocaterId) {
        this.allocaterId = allocaterId;
    }

    @Basic
    @Column(name = "allocater_firstname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAllocaterFirstname() {
        return allocaterFirstname;
    }

    public void setAllocaterFirstname(String allocaterFirstname) {
        this.allocaterFirstname = allocaterFirstname;
    }

    @Basic
    @Column(name = "allocater_middlename", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAllocaterMiddlename() {
        return allocaterMiddlename;
    }

    public void setAllocaterMiddlename(String allocaterMiddlename) {
        this.allocaterMiddlename = allocaterMiddlename;
    }

    @Basic
    @Column(name = "allocater_lastname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAllocaterLastname() {
        return allocaterLastname;
    }

    public void setAllocaterLastname(String allocaterLastname) {
        this.allocaterLastname = allocaterLastname;
    }

    @Id
    @Column(name = "tl_id", nullable = false, insertable = true, updatable = true)
    public int getTlId() {
        return tlId;
    }

    public void setTlId(int tlId) {
        this.tlId = tlId;
    }

    @Basic
    @Column(name = "tl_firstname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTlFirstname() {
        return tlFirstname;
    }

    public void setTlFirstname(String tlFirstname) {
        this.tlFirstname = tlFirstname;
    }

    @Basic
    @Column(name = "tl_middlename", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTlMiddlename() {
        return tlMiddlename;
    }

    public void setTlMiddlename(String tlMiddlename) {
        this.tlMiddlename = tlMiddlename;
    }

    @Basic
    @Column(name = "tl_lastname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTlLastname() {
        return tlLastname;
    }

    public void setTlLastname(String tlLastname) {
        this.tlLastname = tlLastname;
    }

    @Basic
    @Column(name = "allocater_client_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAllocaterClientName() {
        return allocaterClientName;
    }

    public void setAllocaterClientName(String allocaterClientName) {
        this.allocaterClientName = allocaterClientName;
    }

    @Basic
    @Column(name = "tl_client_name", nullable = true, insertable = true, updatable = true, length = 250)
    public String getTlClientName() {
        return tlClientName;
    }

    public void setTlClientName(String tlClientName) {
        this.tlClientName = tlClientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocaterTLMapEntity that = (AllocaterTLMapEntity) o;

        if (allocaterId != that.allocaterId) return false;
        if (tlId != that.tlId) return false;
        if (allocaterClientName != null ? !allocaterClientName.equals(that.allocaterClientName) : that.allocaterClientName != null)
            return false;
        if (allocaterFirstname != null ? !allocaterFirstname.equals(that.allocaterFirstname) : that.allocaterFirstname != null)
            return false;
        if (allocaterLastname != null ? !allocaterLastname.equals(that.allocaterLastname) : that.allocaterLastname != null)
            return false;
        if (allocaterMiddlename != null ? !allocaterMiddlename.equals(that.allocaterMiddlename) : that.allocaterMiddlename != null)
            return false;
        if (tlClientName != null ? !tlClientName.equals(that.tlClientName) : that.tlClientName != null) return false;
        if (tlFirstname != null ? !tlFirstname.equals(that.tlFirstname) : that.tlFirstname != null) return false;
        if (tlLastname != null ? !tlLastname.equals(that.tlLastname) : that.tlLastname != null) return false;
        if (tlMiddlename != null ? !tlMiddlename.equals(that.tlMiddlename) : that.tlMiddlename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = allocaterId;
        result = 31 * result + (allocaterFirstname != null ? allocaterFirstname.hashCode() : 0);
        result = 31 * result + (allocaterMiddlename != null ? allocaterMiddlename.hashCode() : 0);
        result = 31 * result + (allocaterLastname != null ? allocaterLastname.hashCode() : 0);
        result = 31 * result + tlId;
        result = 31 * result + (tlFirstname != null ? tlFirstname.hashCode() : 0);
        result = 31 * result + (tlMiddlename != null ? tlMiddlename.hashCode() : 0);
        result = 31 * result + (tlLastname != null ? tlLastname.hashCode() : 0);
        result = 31 * result + (allocaterClientName != null ? allocaterClientName.hashCode() : 0);
        result = 31 * result + (tlClientName != null ? tlClientName.hashCode() : 0);
        return result;
    }
}
