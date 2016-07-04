
package com.self.dto;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


/**
 * Created by akash.p on 14/6/16.
 */

@JsonPOJOBuilder
public class Bucket {

    @Id
    @Column
    @JsonProperty("bucketName")
    private String bucketName;

    @Column
    @Basic
    @JsonProperty("bucketCount")
    private int bucketCount;

    public Bucket(String bucketName, long bucketCount) {
        this.bucketName = bucketName;
        this.bucketCount = (int)bucketCount;
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

}
