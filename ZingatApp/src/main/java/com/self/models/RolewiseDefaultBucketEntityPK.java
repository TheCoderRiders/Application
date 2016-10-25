package com.self.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by akash.p on 25/10/16.
 */
public class RolewiseDefaultBucketEntityPK implements Serializable {
    private int roleId;
    private int bucketId;

    @Column(name = "role_id")
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "bucket_id")
    @Id
    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolewiseDefaultBucketEntityPK that = (RolewiseDefaultBucketEntityPK) o;

        if (bucketId != that.bucketId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + bucketId;
        return result;
    }
}
