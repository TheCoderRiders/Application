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

    @Field("approximate_synonyms")
    private List<String> approximateSynonyms;

    @Field("code_type")
    private String codeType;

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

    public List<String> getApproximateSynonyms() {
        return approximateSynonyms;
    }

    public void setApproximateSynonyms(List<String> approximateSynonyms) {
        this.approximateSynonyms = approximateSynonyms;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
