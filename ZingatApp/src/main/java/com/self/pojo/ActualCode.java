package com.self.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by akash.p on 28/8/16.
 */
@SolrDocument
public class ActualCode {

    @Field("code_id")
    private String code;

    @Field("code_description")
    private String desc;

    @JsonIgnore
    private Object token;
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

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getPostionList() {
        return postionList;
    }

    public void setPostionList(Object postionList) {
        this.postionList = postionList;
    }

    @Override
    public boolean equals(Object obj) {
        return this.code.equals(((ActualCode)obj).getCode());
    }
}
