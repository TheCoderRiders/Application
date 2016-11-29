package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 28/11/16.
 */
@Entity
@Table(name = "Allocator_auditor_map", schema = "", catalog = "hcc_reportmaster")
@IdClass(AllocatorAuditorMapEntityPK.class)
public class AllocatorAuditorMapEntity {
    private int allocatorId;
    private String allocatorFirstname;
    private String allocatorMiddlename;
    private String allocatorLastname;
    private int auditorId;
    private String auditorFirstname;
    private String auditorMiddlename;
    private String auditorLastname;
    private String allocatorClientName;
    private String auditorClientName;

    @Id
    @Column(name = "Allocator_id")
    public int getAllocatorId() {
        return allocatorId;
    }

    public void setAllocatorId(int allocatorId) {
        this.allocatorId = allocatorId;
    }

    @Basic
    @Column(name = "Allocator_firstname")
    public String getAllocatorFirstname() {
        return allocatorFirstname;
    }

    public void setAllocatorFirstname(String allocatorFirstname) {
        this.allocatorFirstname = allocatorFirstname;
    }

    @Basic
    @Column(name = "Allocator_middlename")
    public String getAllocatorMiddlename() {
        return allocatorMiddlename;
    }

    public void setAllocatorMiddlename(String allocatorMiddlename) {
        this.allocatorMiddlename = allocatorMiddlename;
    }

    @Basic
    @Column(name = "Allocator_lastname")
    public String getAllocatorLastname() {
        return allocatorLastname;
    }

    public void setAllocatorLastname(String allocatorLastname) {
        this.allocatorLastname = allocatorLastname;
    }

    @Id
    @Column(name = "auditor_id")
    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    @Basic
    @Column(name = "auditor_firstname")
    public String getAuditorFirstname() {
        return auditorFirstname;
    }

    public void setAuditorFirstname(String auditorFirstname) {
        this.auditorFirstname = auditorFirstname;
    }

    @Basic
    @Column(name = "auditor_middlename")
    public String getAuditorMiddlename() {
        return auditorMiddlename;
    }

    public void setAuditorMiddlename(String auditorMiddlename) {
        this.auditorMiddlename = auditorMiddlename;
    }

    @Basic
    @Column(name = "auditor_lastname")
    public String getAuditorLastname() {
        return auditorLastname;
    }

    public void setAuditorLastname(String auditorLastname) {
        this.auditorLastname = auditorLastname;
    }

    @Basic
    @Column(name = "Allocator_client_name")
    public String getAllocatorClientName() {
        return allocatorClientName;
    }

    public void setAllocatorClientName(String allocatorClientName) {
        this.allocatorClientName = allocatorClientName;
    }

    @Basic
    @Column(name = "auditor_client_name")
    public String getAuditorClientName() {
        return auditorClientName;
    }

    public void setAuditorClientName(String auditorClientName) {
        this.auditorClientName = auditorClientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllocatorAuditorMapEntity that = (AllocatorAuditorMapEntity) o;

        if (allocatorId != that.allocatorId) return false;
        if (auditorId != that.auditorId) return false;
        if (allocatorClientName != null ? !allocatorClientName.equals(that.allocatorClientName) : that.allocatorClientName != null)
            return false;
        if (allocatorFirstname != null ? !allocatorFirstname.equals(that.allocatorFirstname) : that.allocatorFirstname != null)
            return false;
        if (allocatorLastname != null ? !allocatorLastname.equals(that.allocatorLastname) : that.allocatorLastname != null)
            return false;
        if (allocatorMiddlename != null ? !allocatorMiddlename.equals(that.allocatorMiddlename) : that.allocatorMiddlename != null)
            return false;
        if (auditorClientName != null ? !auditorClientName.equals(that.auditorClientName) : that.auditorClientName != null)
            return false;
        if (auditorFirstname != null ? !auditorFirstname.equals(that.auditorFirstname) : that.auditorFirstname != null)
            return false;
        if (auditorLastname != null ? !auditorLastname.equals(that.auditorLastname) : that.auditorLastname != null)
            return false;
        if (auditorMiddlename != null ? !auditorMiddlename.equals(that.auditorMiddlename) : that.auditorMiddlename != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = allocatorId;
        result = 31 * result + (allocatorFirstname != null ? allocatorFirstname.hashCode() : 0);
        result = 31 * result + (allocatorMiddlename != null ? allocatorMiddlename.hashCode() : 0);
        result = 31 * result + (allocatorLastname != null ? allocatorLastname.hashCode() : 0);
        result = 31 * result + auditorId;
        result = 31 * result + (auditorFirstname != null ? auditorFirstname.hashCode() : 0);
        result = 31 * result + (auditorMiddlename != null ? auditorMiddlename.hashCode() : 0);
        result = 31 * result + (auditorLastname != null ? auditorLastname.hashCode() : 0);
        result = 31 * result + (allocatorClientName != null ? allocatorClientName.hashCode() : 0);
        result = 31 * result + (auditorClientName != null ? auditorClientName.hashCode() : 0);
        return result;
    }
}
