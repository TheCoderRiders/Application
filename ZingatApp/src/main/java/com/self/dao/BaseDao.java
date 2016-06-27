package com.self.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by akash.p on 24/6/16.
 */

@PropertySource("classpath:Queries.properties")
public interface BaseDao {
    @Value("GET_BUCKET_INFO")
    public static final String GET_BUCKET_INFO="";

    @Value("GET_FILE_DETAILS")
    public static final String GET_FILE_DETAILS="";
}
