package com.self.enums;

/**
 * Created by akash.p on 6/7/16.
 */
public enum SortingParameters {
    DOCUMENT_RECEIVED_DATE("document_recived_datetime","Document Received Date"),
    DOCUMENT_ASSIGNED_DATE("document_assigned_datetime","Document Assigned Date"),
    DOCUMENT_REPORT_TYPE("report_type","Document Report Type")
    ;

    private String key,value;

    SortingParameters(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
