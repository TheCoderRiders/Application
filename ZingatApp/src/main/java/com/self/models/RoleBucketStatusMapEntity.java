package com.self.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "role_bucket_status_map", schema = "", catalog = "hcc_reportmaster")
public class RoleBucketStatusMapEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String roleName;
    private String bucketValue;
    private String statusCssClass;
    private String statusValue;
    private int roleId;
    private int statusId;

    @Column(name = "status_id", nullable = false, insertable = true, updatable = true)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 65535)
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
    @Column(name = "status_css_class", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getStatusCssClass() {
        return statusCssClass;
    }

    public void setStatusCssClass(String statusCssClass) {
        this.statusCssClass = statusCssClass;
    }

    @Basic
    @Column(name = "status_value", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleBucketStatusMapEntity that = (RoleBucketStatusMapEntity) o;

        if (id != that.id) return false;
        if (bucketValue != null ? !bucketValue.equals(that.bucketValue) : that.bucketValue != null) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (statusCssClass != null ? !statusCssClass.equals(that.statusCssClass) : that.statusCssClass != null)
            return false;
        if (statusValue != null ? !statusValue.equals(that.statusValue) : that.statusValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (bucketValue != null ? bucketValue.hashCode() : 0);
        result = 31 * result + (statusCssClass != null ? statusCssClass.hashCode() : 0);
        result = 31 * result + (statusValue != null ? statusValue.hashCode() : 0);
        return result;
    }
}
