package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "role_bucket_rightside_map", schema = "", catalog = "hcc_reportmaster")
public class RoleBucketRightsideMapEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String roleName;
    private Integer roleId;
    private Integer bucketId;
    private String bucketValue;
    private String rightsideColumnKey;
    private String rightsideColumnName;
    private Integer rightsideColumnSequence;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "bucket_value", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBucketValue() {
        return bucketValue;
    }

    public void setBucketValue(String bucketValue) {
        this.bucketValue = bucketValue;
    }

    @Basic
    @Column(name = "rightside_column_key", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRightsideColumnKey() {
        return rightsideColumnKey;
    }

    public void setRightsideColumnKey(String rightsideColumnKey) {
        this.rightsideColumnKey = rightsideColumnKey;
    }

    @Basic
    @Column(name = "rightside_column_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRightsideColumnName() {
        return rightsideColumnName;
    }

    public void setRightsideColumnName(String rightsideColumnName) {
        this.rightsideColumnName = rightsideColumnName;
    }

    @Basic
    @Column(name = "rightside_column_sequence", nullable = true, insertable = true, updatable = true)
    public Integer getRightsideColumnSequence() {
        return rightsideColumnSequence;
    }

    public void setRightsideColumnSequence(Integer rightsideColumnSequence) {
        this.rightsideColumnSequence = rightsideColumnSequence;
    }

    @Basic
    @Column(name = "role_id", nullable = true, insertable = true, updatable = true)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "bucket_id", nullable = true, insertable = true, updatable = true)
    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleBucketRightsideMapEntity that = (RoleBucketRightsideMapEntity) o;

        if (id != that.id) return false;
        if (bucketValue != null ? !bucketValue.equals(that.bucketValue) : that.bucketValue != null) return false;
        if (rightsideColumnKey != null ? !rightsideColumnKey.equals(that.rightsideColumnKey) : that.rightsideColumnKey != null)
            return false;
        if (rightsideColumnName != null ? !rightsideColumnName.equals(that.rightsideColumnName) : that.rightsideColumnName != null)
            return false;
        if (rightsideColumnSequence != null ? !rightsideColumnSequence.equals(that.rightsideColumnSequence) : that.rightsideColumnSequence != null)
            return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (bucketValue != null ? bucketValue.hashCode() : 0);
        result = 31 * result + (rightsideColumnKey != null ? rightsideColumnKey.hashCode() : 0);
        result = 31 * result + (rightsideColumnName != null ? rightsideColumnName.hashCode() : 0);
        result = 31 * result + (rightsideColumnSequence != null ? rightsideColumnSequence.hashCode() : 0);
        return result;
    }
}
