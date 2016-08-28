package com.self.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCodeInfo {

    private String sectionName;
    private List<ActualCode> codes;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<ActualCode> getCodes() {
        return codes;
    }

    public void setCodes(List<ActualCode> codes) {
        this.codes = codes;
    }
}
