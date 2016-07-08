
package com.self.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by akash.p on 14/6/16.
 */

public class BucketActions {

    @JsonProperty("buckets")
    private List<Bucket> buckets = new ArrayList<Bucket>();
    @JsonProperty("actions")
    private Actions actions;

    @JsonProperty("sortParams")
    private Actions sortParams;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The buckets
     */
    @JsonProperty("buckets")
    public List<Bucket> getBuckets() {
        return buckets;
    }

    /**
     * 
     * @param buckets
     *     The buckets
     */
    @JsonProperty("buckets")
    public void setBuckets(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    /**
     * 
     * @return
     *     The actions
     */
    @JsonProperty("actions")
    public Actions getActions() {
        return actions;
    }

    /**
     * 
     * @param actions
     *     The actions
     */
    @JsonProperty("actions")
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Actions getSortParams() {
        return sortParams;
    }

    public void setSortParams(Actions sortParams) {
        this.sortParams = sortParams;
    }
}
