package com.self.util.solr;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.params.ModifiableSolrParams;
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
                                           @Value("${solr.core.icd.code.search}") String solrCodeSearchCore,
                                           @Value("${solr.connection.max}") Integer maxConnection,
                                           @Value("${solr.connection.max.per.host}") Integer maxConnectionPerHost,
                                           @Value("${solr.connection.follow.redirects}") Boolean followRedirects,
                                           @Value("${solr.connection.user.name}") String userName,
                                           @Value("${solr.connection.user.password}") String password){

        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, maxConnection);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, maxConnectionPerHost);
        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, followRedirects);
        params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, userName);
        params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, password);
        //HttpClientUtil.addRequestInterceptor(new PreemptiveAuthInterceptor());
        HttpClient httpClient = HttpClientUtil.createClient(params);
        return new HttpSolrServer(solrServerUrl+"/"+solrCodeSearchCore,httpClient);
    }
}
