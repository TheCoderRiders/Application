package com.self.business;

import com.fasterxml.jackson.core.JsonParseException;
import com.self.dto.Codes;

import java.io.IOException;

/**
 * Created by akash.p on 25/7/16.
 */
public interface WorkingPageBusiness {
    public Codes getCodes(String fileId) throws IOException;
}
