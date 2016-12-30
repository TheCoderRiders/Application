package com.self.dto;

/**
 * Created by akash.p on 30/12/16.
 */
public class SearchSuggestionRequest {
    private String fieldKey;
    private String searchValue;

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
