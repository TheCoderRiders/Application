package com.self.dto;

import com.self.models.DocRejectionReasonListEntity;

/**
 * Created by akash.p on 8/7/16.
 */
public class FileContent {
    private String data;
    private String fileMode;
    private DocRejectionReasonListEntity documentRejectionReason;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFileMode() {
        return fileMode;
    }

    public void setFileMode(String fileMode) {
        this.fileMode = fileMode;
    }

    public DocRejectionReasonListEntity getDocumentRejectionReason() {
        return documentRejectionReason;
    }

    public void setDocumentRejectionReason(DocRejectionReasonListEntity documentRejectionReason) {
        this.documentRejectionReason = documentRejectionReason;
    }
}
