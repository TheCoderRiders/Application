package com.self.dto;

import com.self.constants.Constants;

/**
 * Created by akash.p on 30/12/16.
 */
public class SearchSuggestionResponse {

    private boolean isField;
    private String fieldKey;
    private String fieldName;
    private String fieldType;
    private String fieldDisplayValue;

    public SearchSuggestionResponse(boolean isField, Object fieldKey, Object fieldName, Object fieldDisplayValue) {
        this.isField = isField;
        this.fieldKey = String.valueOf(fieldKey);
        this.fieldName = String.valueOf(fieldName);
        this.fieldType = this.fieldKey.contains(Constants.DATE)?Constants.DATE:Constants.VALUE;
        this.fieldDisplayValue = String.valueOf(fieldDisplayValue);
    }

    public boolean isField() {
        return isField;
    }

    public void setField(boolean isField) {
        this.isField = isField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDisplayValue() {
        return fieldDisplayValue;
    }

    public void setFieldDisplayValue(String fieldDisplayValue) {
        this.fieldDisplayValue = fieldDisplayValue;
    }
}
