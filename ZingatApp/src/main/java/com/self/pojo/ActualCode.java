package com.self.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

/**
 * Created by akash.p on 28/8/16.
 */
@SolrDocument
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualCode {

    @Field("code_id")
    private String code;

    @Field("code_description")
    private String desc;

    private String type;

    private List<String> token;

    private Integer rejectionReasonListId;

    private String rejectionReasonDisplay;

    private String rejectionReasonDesc;

    @JsonIgnore
    private Object postionList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getToken() {
        return token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPostionList(Object postionList) {
        this.postionList = postionList;
    }

    public Integer getRejectionReasonListId() {
        return rejectionReasonListId;
    }

    public void setRejectionReasonListId(Integer rejectionReasonListId) {
        this.rejectionReasonListId = rejectionReasonListId;
    }

    public String getRejectionReasonDisplay() {
        return rejectionReasonDisplay;
    }

    public void setRejectionReasonDisplay(String rejectionReasonDisplay) {
        this.rejectionReasonDisplay = rejectionReasonDisplay;
    }

    public String getRejectionReasonDesc() {
        return rejectionReasonDesc;
    }

    public void setRejectionReasonDesc(String rejectionReasonDesc) {
        this.rejectionReasonDesc = rejectionReasonDesc;
    }

    @Override
    public boolean equals(Object obj) {
        return this.code.equals(((ActualCode)obj).getCode());
    }
}
