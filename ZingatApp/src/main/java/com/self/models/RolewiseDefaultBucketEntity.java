package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 25/10/16.
 */
@Entity
@Table(name = "rolewise_default_bucket", schema = "", catalog = "hcc_reportmaster")
@IdClass(RolewiseDefaultBucketEntityPK.class)
public class RolewiseDefaultBucketEntity {
    private int roleId;
    private String roleName;
    private int bucketId;
    private String bucketName;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @Column(name = "bucket_id")
    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    @Basic
    @Column(name = "bucket_name")
    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolewiseDefaultBucketEntity that = (RolewiseDefaultBucketEntity) o;

        if (bucketId != that.bucketId) return false;
        if (roleId != that.roleId) return false;
        if (bucketName != null ? !bucketName.equals(that.bucketName) : that.bucketName != null) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + bucketId;
        result = 31 * result + (bucketName != null ? bucketName.hashCode() : 0);
        return result;
    }
}
