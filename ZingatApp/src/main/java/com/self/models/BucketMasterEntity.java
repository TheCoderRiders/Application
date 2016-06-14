package com.self.models;

import javax.persistence.*;

/**
 * Created by akash.p on 14/6/16.
 */
@Entity
@Table(name = "bucket_master", schema = "", catalog = "zingat")
public class BucketMasterEntity {
    private int bucketId;
    private String bucketValue;
    private String bucketDescription;

    @Id
    @Column(name = "bucket_id", nullable = false, insertable = true, updatable = true)
    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    @Basic
    @Column(name = "bucket_value", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getBucketValue() {
        return bucketValue;
    }

    public void setBucketValue(String bucketValue) {
        this.bucketValue = bucketValue;
    }

    @Basic
    @Column(name = "bucket_description", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getBucketDescription() {
        return bucketDescription;
    }

    public void setBucketDescription(String bucketDescription) {
        this.bucketDescription = bucketDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BucketMasterEntity that = (BucketMasterEntity) o;

        if (bucketId != that.bucketId) return false;
        if (bucketDescription != null ? !bucketDescription.equals(that.bucketDescription) : that.bucketDescription != null)
            return false;
        if (bucketValue != null ? !bucketValue.equals(that.bucketValue) : that.bucketValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bucketId;
        result = 31 * result + (bucketValue != null ? bucketValue.hashCode() : 0);
        result = 31 * result + (bucketDescription != null ? bucketDescription.hashCode() : 0);
        return result;
    }
}
