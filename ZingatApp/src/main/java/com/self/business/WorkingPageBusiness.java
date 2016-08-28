package com.self.business;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.self.dto.Codes;

import java.io.IOException;

/**
 * Created by akash.p on 25/7/16.
 */
public interface WorkingPageBusiness {
    public Codes getCodes(String fileId) throws IOException;

    public Boolean saveCodes(Codes codes) throws JsonProcessingException;
}
