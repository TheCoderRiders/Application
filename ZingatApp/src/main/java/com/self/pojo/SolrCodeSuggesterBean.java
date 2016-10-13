package com.self.pojo;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

/**
 * Created by akash.p on 13/10/16.
 */

@SolrDocument
public class SolrCodeSuggesterBean {

    @Field("id")
    private String id;

    @Field("code_id")
    private String codeId;

    @Field("search_field_ngram")
    private List<String> searchFieldNgram;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSearchFieldNgram() {
        return searchFieldNgram;
    }

    public void setSearchFieldNgram(List<String> searchFieldNgram) {
        this.searchFieldNgram = searchFieldNgram;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
}
