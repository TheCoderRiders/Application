package com.self.util.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * Created by akash.p on 5/9/16.
 */

@Configuration
public class SolrUtil {

    @Bean
    public SolrServer codeSearchSolrServer(@Value("${solr.server}") String solrServerUrl,
                                     @Value("${solr.core.icd.code.search}") String solrCodeSearchCore){
        return new HttpSolrServer(solrServerUrl+"/"+solrCodeSearchCore);
    }
}
