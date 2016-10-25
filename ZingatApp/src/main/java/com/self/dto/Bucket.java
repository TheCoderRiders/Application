
package com.self.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;


/**
 * Created by akash.p on 14/6/16.
 */

@JsonPOJOBuilder
public class Bucket implements Comparable<Bucket> {

    @Id
    @Column
    @JsonProperty("bucketName")
    private String bucketName;

    @Column
    @Basic
    @JsonProperty("bucketCount")
    private int bucketCount;

    private Boolean defaultBucket = Boolean.FALSE;

    public Bucket(String bucketName, long bucketCount) {
        this.bucketName = bucketName;
        this.bucketCount = (int)bucketCount;
    }

    public Bucket() {

    }

    /**
     * 
     * @return
     *     The bucketName
     */
    @JsonProperty("bucketName")
    public String getBucketName() {
        return bucketName;
    }

    /**
     * 
     * @param bucketName
     *     The bucketName
     */
    @JsonProperty("bucketName")
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 
     * @return
     *     The bucketCount
     */
    @JsonProperty("bucketCount")
    public int getBucketCount() {
        return bucketCount;
    }

    /**
     * 
     * @param bucketCount
     *     The bucketCount
     */
    @JsonProperty("bucketCount")
    public void setBucketCount(int bucketCount) {
        this.bucketCount = bucketCount;
    }

    @Override
    public int compareTo(Bucket o) {
        int defaultBucketCompare = o.getDefaultBucket().compareTo(this.getDefaultBucket());
        if(defaultBucketCompare!=0) return defaultBucketCompare;
        return o.getBucketName().compareTo(this.getBucketName());
    }

    public Boolean getDefaultBucket() {
        return defaultBucket;
    }

    public void setDefaultBucket(Boolean defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    @Override
    public boolean equals(Object obj) {
        return this.bucketName.equals(((Bucket) obj).getBucketName());
    }
}
