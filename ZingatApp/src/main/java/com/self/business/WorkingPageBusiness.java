package com.self.business;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.self.dto.CodeAction;
import com.self.dto.Codes;
import com.self.enums.FileStatus;
import com.self.pojo.ActualCode;

import java.io.IOException;
import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
public interface WorkingPageBusiness {
    public Codes getCodes(String fileId) throws IOException;

    public Boolean saveCodes(Codes codes) throws JsonProcessingException;

    public com.self.dto.CodeSearchResult searchCode(String key, Integer start);

    public Codes codeAction(CodeAction codeAction) throws JsonProcessingException;

    public Boolean documentStatusChange(String fileId, FileStatus status);
}
