package com.self.dto;

import com.self.enums.FileStatus;
import com.self.models.DocRejectionReasonListEntity;

/**
 * Created by akash.p on 14/10/16.
 */
public class FileStatusChangeRequest {

    private String fileId;
    private FileStatus status;
    private DocRejectionReasonListEntity docRejectionReason;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public DocRejectionReasonListEntity getDocRejectionReason() {
        return docRejectionReason;
    }

    public void setDocRejectionReason(DocRejectionReasonListEntity docRejectionReason) {
        this.docRejectionReason = docRejectionReason;
    }
}
