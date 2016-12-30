package com.self.business;

import com.self.dto.SearchSuggestionRequest;
import com.self.dto.SearchSuggestionResponse;

import java.util.List;

/**
 * Created by akash.p on 30/12/16.
 */
public interface SearchBusiness {
    public List<SearchSuggestionResponse> getSuggestion(SearchSuggestionRequest searchSuggestionRequest);
}
