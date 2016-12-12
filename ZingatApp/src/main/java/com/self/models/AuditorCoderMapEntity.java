package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 28/11/16.
 */
@Entity
@Table(name = "auditor_coder_map", schema = "", catalog = "hcc_reportmaster")
@IdClass(AuditorCoderMapEntityPK.class)
public class AuditorCoderMapEntity {
    private int auditorId;
    private String auditorFirstname;
    private String auditorMiddlename;
    private String auditorLastname;
    private String auditorUserName;
    private int coderId;
    private String coderFirstname;
    private String coderMiddlename;
    private String coderLastname;
    private String coderUserName;
    private String auditorClientName;
    private String coderClientName;

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

    @Id
    @Column(name = "coder_id")
    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    @Basic
    @Column(name = "coder_firstname")
    public String getCoderFirstname() {
        return coderFirstname;
    }

    public void setCoderFirstname(String coderFirstname) {
        this.coderFirstname = coderFirstname;
    }

    @Basic
    @Column(name = "coder_middlename")
    public String getCoderMiddlename() {
        return coderMiddlename;
    }

    public void setCoderMiddlename(String coderMiddlename) {
        this.coderMiddlename = coderMiddlename;
    }

    @Basic
    @Column(name = "coder_lastname")
    public String getCoderLastname() {
        return coderLastname;
    }

    public void setCoderLastname(String coderLastname) {
        this.coderLastname = coderLastname;
    }

    @Basic
    @Column(name = "auditor_client_name")
    public String getAuditorClientName() {
        return auditorClientName;
    }

    public void setAuditorClientName(String auditorClientName) {
        this.auditorClientName = auditorClientName;
    }

    @Basic
    @Column(name = "coder_client_name")
    public String getCoderClientName() {
        return coderClientName;
    }

    public void setCoderClientName(String coderClientName) {
        this.coderClientName = coderClientName;
    }

    @Basic
    @Column(name = "Auditor_username")
    public String getAuditorUserName() {
        return auditorUserName;
    }

    public void setAuditorUserName(String auditorUserName) {
        this.auditorUserName = auditorUserName;
    }

    @Basic
    @Column(name = "coder_username")
    public String getCoderUserName() {
        return coderUserName;
    }

    public void setCoderUserName(String coderUserName) {
        this.coderUserName = coderUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditorCoderMapEntity that = (AuditorCoderMapEntity) o;

        if (auditorId != that.auditorId) return false;
        if (coderId != that.coderId) return false;
        if (auditorClientName != null ? !auditorClientName.equals(that.auditorClientName) : that.auditorClientName != null)
            return false;
        if (auditorFirstname != null ? !auditorFirstname.equals(that.auditorFirstname) : that.auditorFirstname != null)
            return false;
        if (auditorLastname != null ? !auditorLastname.equals(that.auditorLastname) : that.auditorLastname != null)
            return false;
        if (auditorMiddlename != null ? !auditorMiddlename.equals(that.auditorMiddlename) : that.auditorMiddlename != null)
            return false;
        if (coderClientName != null ? !coderClientName.equals(that.coderClientName) : that.coderClientName != null)
            return false;
        if (coderFirstname != null ? !coderFirstname.equals(that.coderFirstname) : that.coderFirstname != null)
            return false;
        if (coderLastname != null ? !coderLastname.equals(that.coderLastname) : that.coderLastname != null)
            return false;
        if (coderMiddlename != null ? !coderMiddlename.equals(that.coderMiddlename) : that.coderMiddlename != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auditorId;
        result = 31 * result + (auditorFirstname != null ? auditorFirstname.hashCode() : 0);
        result = 31 * result + (auditorMiddlename != null ? auditorMiddlename.hashCode() : 0);
        result = 31 * result + (auditorLastname != null ? auditorLastname.hashCode() : 0);
        result = 31 * result + coderId;
        result = 31 * result + (coderFirstname != null ? coderFirstname.hashCode() : 0);
        result = 31 * result + (coderMiddlename != null ? coderMiddlename.hashCode() : 0);
        result = 31 * result + (coderLastname != null ? coderLastname.hashCode() : 0);
        result = 31 * result + (auditorClientName != null ? auditorClientName.hashCode() : 0);
        result = 31 * result + (coderClientName != null ? coderClientName.hashCode() : 0);
        return result;
    }
}
