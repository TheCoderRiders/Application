package com.self.dto;

import com.self.models.DocRejectionReasonListEntity;

import java.util.List;

/**
 * Created by akash.p on 8/7/16.
 */
public class FileContent {
    private String data;
    private String fileMode;
    private DocRejectionReasonListEntity documentRejectionReason;
    private List<DoubtRebuttalInfo> doubtRebuttalInfoList;
    private int rebuttalCount;

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

    public void setDoubtRebuttalInfoList(List<DoubtRebuttalInfo> doubtRebuttalInfoList) {
        this.doubtRebuttalInfoList = doubtRebuttalInfoList;
    }

    public List<DoubtRebuttalInfo> getDoubtRebuttalInfoList() {
        return doubtRebuttalInfoList;
    }

    public int getRebuttalCount() {
        return rebuttalCount;
    }

    public void setRebuttalCount(int rebuttalCount) {
        this.rebuttalCount = rebuttalCount;
    }
}
