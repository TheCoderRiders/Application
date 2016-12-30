package com.self.business;

import com.self.constants.Constants;
import com.self.dao.SearchRepository;
import com.self.dto.SearchSuggestionRequest;
import com.self.dto.SearchSuggestionResponse;
import com.self.models.DocumentMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by akash.p on 30/12/16.
 */
@Component
public class SearchBusinessImpl implements SearchBusiness {

    public static Properties properties = new Properties();

    @Autowired
    private SearchRepository searchRepository;

    @PostConstruct
    public void setDocumentSearchField() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        properties = PropertiesLoaderUtils.loadProperties(resourceLoader.getResource(Constants.DOCUMENT_SEARCH_FIELD_PROP_FILE));
    }

    @Override
    public List<SearchSuggestionResponse> getSuggestion(SearchSuggestionRequest searchSuggestionRequest) {
        String fieldKey = searchSuggestionRequest.getFieldKey();
        String searchValue = searchSuggestionRequest.getSearchValue();
        int maxSearchSuggestionValue = Constants.MAX_SEARCH_SUGGESTION_VALUE;
        final Set<SearchSuggestionResponse> searchSuggestionResponses = new HashSet<>();
        if(fieldKey==null || fieldKey.isEmpty()){
           searchSuggestionResponses.addAll(getSuggestionForDocumentSearchFields(searchValue));
           getDocumentSearchFieldsKeys().forEach(key ->{
               if(searchSuggestionResponses.size()== maxSearchSuggestionValue)  return;
               searchSuggestionResponses.addAll(getSuggestionForDocumentMasterValues(key.toString(), searchValue));
           });
        }else {
            searchSuggestionResponses.addAll(getSuggestionForDocumentMasterValues(fieldKey, searchValue));
        }
        int size = searchSuggestionResponses.size();
        return new LinkedList<>(searchSuggestionResponses).subList(0, size >maxSearchSuggestionValue?maxSearchSuggestionValue:size);
    }

    private List<Object> getDocumentSearchFieldsKeys() {
        return new ArrayList<>(properties.keySet());
    }

    private Collection<? extends SearchSuggestionResponse> getSuggestionForDocumentMasterValues(String fieldKey, String searchValue) {
        Set<SearchSuggestionResponse> searchSuggestionResponses = new HashSet<>();
        List<String> result = new ArrayList<>();
        switch (fieldKey){
            case Constants.DOCUMENT_NAME:
                result = searchRepository.getCriteriaByRestrictions(searchValue, DocumentMasterEntity.class, "documentName");
                break;
            case Constants.USER_NAME:
                result = searchRepository.getCriteriaByRestrictions(searchValue, DocumentMasterEntity.class, "documentAssignedName");
                break;
        }
        result.forEach(searchResult -> searchSuggestionResponses.add((new SearchSuggestionResponse(false, fieldKey, searchResult, searchResult + Constants.IN + properties.get(fieldKey)))));
        return searchSuggestionResponses;
    }

    private Set<SearchSuggestionResponse> getSuggestionForDocumentSearchFields(String searchValue) {
        Set<SearchSuggestionResponse> searchSuggestionResponses = new HashSet<>();
        Map<Object, Object> searchMap = properties.entrySet().stream().filter(pro -> pro.getValue().toString().toLowerCase().contains(searchValue)).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        searchMap.forEach((key,value)->searchSuggestionResponses.add(new SearchSuggestionResponse(true,key,value,value)));
        return searchSuggestionResponses;
    }
}
