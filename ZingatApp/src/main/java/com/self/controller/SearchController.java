package com.self.controller;

import com.self.business.SearchBusiness;
import com.self.dto.SearchSuggestionRequest;
import com.self.dto.SearchSuggestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by akash.p on 30/12/16.
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchBusiness searchBusiness;

    @RequestMapping(value = "/getSuggestion")
    public List<SearchSuggestionResponse> getSuggestion(@RequestBody SearchSuggestionRequest searchSuggestionRequest) throws IOException {
        return searchBusiness.getSuggestion(searchSuggestionRequest);
    }
}
