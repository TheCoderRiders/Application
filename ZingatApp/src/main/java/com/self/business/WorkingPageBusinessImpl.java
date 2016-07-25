package com.self.business;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.dto.Codes;
import com.self.models.DocumentMasterEntity;
import com.self.pojo.CodeInfo;
import com.self.service.WorkingPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by akash.p on 25/7/16.
 */
@Component
public class WorkingPageBusinessImpl implements WorkingPageBusiness {

    @Autowired
    private WorkingPageService workingPageService;

    @Override
    public Codes getCodes(String fileId) throws IOException {
        DocumentMasterEntity documentMasterEntity = workingPageService.getCodes(fileId);
        Codes acceptedSuggestedRejected = new Codes();
        ObjectMapper objectMapper = new ObjectMapper();

        acceptedSuggestedRejected.setAcceptedCode(Arrays.asList(
                objectMapper.readValue(documentMasterEntity.getDocumentAcceptedCodes(), CodeInfo.class)));

        acceptedSuggestedRejected.setRejectedCode(Arrays.asList(
                objectMapper.readValue(documentMasterEntity.getDocumentRejectedCodes(), CodeInfo.class)));

        acceptedSuggestedRejected.setSuggestedCode(Arrays.asList(
                objectMapper.readValue(documentMasterEntity.getDocumentSuggestedCodes(), CodeInfo.class)));

        return acceptedSuggestedRejected;
    }
}
