package com.self.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.self.dto.AcknowledgeCommentInfo;
import com.self.dto.CodeAction;
import com.self.dto.Codes;
import com.self.dto.FileStatusChangeRequest;
import com.self.models.CodeRejectionReasonListEntity;
import com.self.models.DocRejectionReasonListEntity;
import com.self.models.DoubtListEntity;
import com.self.models.RebuttalListEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
public interface WorkingPageBusiness {
    public Codes getCodes(String fileId, String currentRole) throws IOException;

    public Boolean saveCodes(Codes codes) throws JsonProcessingException;

    public com.self.dto.CodeSearchResult searchCode(String key, Integer start);

    public Codes codeAction(CodeAction codeAction) throws JsonProcessingException;

    public Boolean documentStatusChange(FileStatusChangeRequest fileStatusChangeRequest) throws IOException;

    public List<CodeRejectionReasonListEntity> getCodeRejectionReasonList();

    public List<DocRejectionReasonListEntity> getDocRejectionReasonList();

    public File getDownloadFile(String fileId) throws Exception;

    public List getDoubtList();

    public List getRebuttalList();

    public Boolean acknowledgeComment(AcknowledgeCommentInfo acknowledgeCommentInfo);
}
