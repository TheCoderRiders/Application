package com.self.dto;

/**
 * Created by akash.p on 22/10/16.
 */
public class RightSideColumnResponse {

    private String columnName;
    private String columnValue;
    private Integer columnSequenceNumber;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public Integer getColumnSequenceNumber() {
        return columnSequenceNumber;
    }

    public void setColumnSequenceNumber(Integer columnSequenceNumber) {
        this.columnSequenceNumber = columnSequenceNumber;
    }
}
