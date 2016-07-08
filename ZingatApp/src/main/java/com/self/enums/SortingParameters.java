package com.self.enums;

/**
 * Created by akash.p on 6/7/16.
 */
public enum SortingParameters {
    DOCUMENT_RECEIVED_DATE("documentRecivedDatetime","Document Received Date"),
    DOCUMENT_ASSIGNED_DATE("documentAssignedDatetime","Document Assigned Date"),
    DOCUMENT_REPORT_TYPE("reportType","Document Report Type")
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
