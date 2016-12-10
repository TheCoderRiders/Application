package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "tl_coder_map", schema = "", catalog = "hcc_reportmaster")
@IdClass(TLCoderMapEntityPK.class)
public class TLCoderMapEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int tlId;
    private String tlFirstname;
    private String tlMiddlename;
    private String tlLastname;
    private String tlUserName;
    private int coderId;
    private String coderFirstname;
    private String coderMiddlename;
    private String coderLastname;
    private String coderUserName;
    private String tlClientName;
    private String coderClientName;
    private Integer tlClientId;
    private Integer coderClientId;
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

    @Id
    @Column(name = "coder_id", nullable = false, insertable = true, updatable = true)
    public int getCoderId() {
        return coderId;
    }

    public void setCoderId(int coderId) {
        this.coderId = coderId;
    }

    @Basic
    @Column(name = "coder_firstname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCoderFirstname() {
        return coderFirstname;
    }

    public void setCoderFirstname(String coderFirstname) {
        this.coderFirstname = coderFirstname;
    }

    @Basic
    @Column(name = "coder_middlename", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCoderMiddlename() {
        return coderMiddlename;
    }

    public void setCoderMiddlename(String coderMiddlename) {
        this.coderMiddlename = coderMiddlename;
    }

    @Basic
    @Column(name = "coder_lastname", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCoderLastname() {
        return coderLastname;
    }

    public void setCoderLastname(String coderLastname) {
        this.coderLastname = coderLastname;
    }

    @Basic
    @Column(name = "tl_client_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTlClientName() {
        return tlClientName;
    }

    public void setTlClientName(String tlClientName) {
        this.tlClientName = tlClientName;
    }

    @Basic
    @Column(name = "coder_client_name", nullable = true, insertable = true, updatable = true, length = 250)
    public String getCoderClientName() {
        return coderClientName;
    }

    public void setCoderClientName(String coderClientName) {
        this.coderClientName = coderClientName;
    }

    @Column(name = "tl_client_id", nullable = false, insertable = true, updatable = true)
    public Integer getTlClientId() {
        return tlClientId;
    }

    public void setTlClientId(Integer tlClientId) {
        this.tlClientId = tlClientId;
    }

    @Column(name = "coder_client_id", nullable = false, insertable = true, updatable = true)
    public Integer getCoderClientId() {
        return coderClientId;
    }

    public void setCoderClientId(Integer coderClientId) {
        this.coderClientId = coderClientId;
    }

    @Basic
    @Column(name = "Tl_username", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTlUserName() {
        return tlUserName;
    }

    public void setTlUserName(String tlUserName) {
        this.tlUserName = tlUserName;
    }

    @Basic
    @Column(name = "coder_username", nullable = true, insertable = true, updatable = true, length = 50)
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

        TLCoderMapEntity that = (TLCoderMapEntity) o;

        if (coderId != that.coderId) return false;
        if (tlId != that.tlId) return false;
        if (coderClientName != null ? !coderClientName.equals(that.coderClientName) : that.coderClientName != null)
            return false;
        if (coderFirstname != null ? !coderFirstname.equals(that.coderFirstname) : that.coderFirstname != null)
            return false;
        if (coderLastname != null ? !coderLastname.equals(that.coderLastname) : that.coderLastname != null)
            return false;
        if (coderMiddlename != null ? !coderMiddlename.equals(that.coderMiddlename) : that.coderMiddlename != null)
            return false;
        if (tlClientName != null ? !tlClientName.equals(that.tlClientName) : that.tlClientName != null) return false;
        if (tlFirstname != null ? !tlFirstname.equals(that.tlFirstname) : that.tlFirstname != null) return false;
        if (tlLastname != null ? !tlLastname.equals(that.tlLastname) : that.tlLastname != null) return false;
        if (tlMiddlename != null ? !tlMiddlename.equals(that.tlMiddlename) : that.tlMiddlename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tlId;
        result = 31 * result + (tlFirstname != null ? tlFirstname.hashCode() : 0);
        result = 31 * result + (tlMiddlename != null ? tlMiddlename.hashCode() : 0);
        result = 31 * result + (tlLastname != null ? tlLastname.hashCode() : 0);
        result = 31 * result + coderId;
        result = 31 * result + (coderFirstname != null ? coderFirstname.hashCode() : 0);
        result = 31 * result + (coderMiddlename != null ? coderMiddlename.hashCode() : 0);
        result = 31 * result + (coderLastname != null ? coderLastname.hashCode() : 0);
        result = 31 * result + (tlClientName != null ? tlClientName.hashCode() : 0);
        result = 31 * result + (coderClientName != null ? coderClientName.hashCode() : 0);
        return result;
    }
}
