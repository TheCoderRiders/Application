package com.self.dao;

import com.self.pojo.ActualCode;
import com.self.pojo.SolrCodeSuggesterBean;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by akash.p on 5/9/16.
 */
@Repository
public class SolrSuggesterRepository {

    @Autowired
    private SolrServer codeSearchSolrServer;

    @Value("${code.search.pagination.size}")
    private Integer paginationSize;

    public QueryResponse searchCode(String key, Integer start) {
        SolrQuery solrQuery = new SolrQuery("search_field_ngram:*"+key+"*");
        solrQuery.setFilterQueries("icd_09_10:\"ICD10\"");
        solrQuery.setStart(start-1);
        solrQuery.setRows(paginationSize);

        QueryResponse queryResponse = null;
        try {
            queryResponse = codeSearchSolrServer.query(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResponse;
    }

    public Integer getPaginationSize() {
        return paginationSize;
    }

    public void saveCodeSuggesterBean(List<SolrCodeSuggesterBean> solrCodeSuggesterBeanList) {
        List<SolrInputDocument> solrInputDocumentList = new ArrayList<>();
        solrCodeSuggesterBeanList.forEach(solrCodeSuggesterBean->{
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id",solrCodeSuggesterBean.getId());
            solrInputDocument.addField("code_id",solrCodeSuggesterBean.getCodeId());
            solrInputDocument.addField("search_field_ngram", Collections.singletonMap("set", solrCodeSuggesterBean.getSearchFieldNgram()));
            if(solrCodeSuggesterBean.getSearchFieldNgram().size()!=0)
                solrInputDocumentList.add(solrInputDocument);
        });

        try {
            if(solrInputDocumentList.size()!=0) {
                codeSearchSolrServer.add(solrInputDocumentList);
                codeSearchSolrServer.commit();
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueryResponse findAll(Integer start,Integer rows) {
        SolrQuery solrQuery = new SolrQuery("*:*");
        solrQuery.setFilterQueries("icd_09_10:\"ICD10\"");
        solrQuery.setStart(start-1);
        solrQuery.setRows(rows);

        QueryResponse queryResponse = null;
        try {
            queryResponse = codeSearchSolrServer.query(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResponse;
    }

    public QueryResponse findCode(String key) {
        SolrQuery solrQuery = new SolrQuery("search_field:\""+key+"\"");
        solrQuery.setFilterQueries("icd_09_10:\"ICD10\"");
        solrQuery.setStart(0);
        solrQuery.setRows(Integer.MAX_VALUE);

        QueryResponse queryResponse = null;
        try {
            queryResponse = codeSearchSolrServer.query(solrQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryResponse;
    }
}
