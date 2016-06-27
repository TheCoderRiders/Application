
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

public class Actions {

    @JsonProperty("availableOptions")
    private List<AvailableOption> availableOptions = new ArrayList<AvailableOption>();
    @JsonProperty("selectedOption")
    private AvailableOption selectedOption;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The availableOptions
     */
    @JsonProperty("availableOptions")
    public List<AvailableOption> getAvailableOptions() {
        return availableOptions;
    }

    /**
     * 
     * @param availableOptions
     *     The availableOptions
     */
    @JsonProperty("availableOptions")
    public void setAvailableOptions(List<AvailableOption> availableOptions) {
        this.availableOptions = availableOptions;
    }

    /**
     * 
     * @return
     *     The selectedOption
     */
    @JsonProperty("selectedOption")
    public AvailableOption getSelectedOption() {
        return selectedOption;
    }

    /**
     * 
     * @param selectedOption
     *     The selectedOption
     */
    @JsonProperty("selectedOption")
    public void setSelectedOption(AvailableOption selectedOption) {
        this.selectedOption = selectedOption;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
