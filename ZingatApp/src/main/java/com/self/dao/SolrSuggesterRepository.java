package com.self.dao;

import com.self.pojo.ActualCode;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
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
}
