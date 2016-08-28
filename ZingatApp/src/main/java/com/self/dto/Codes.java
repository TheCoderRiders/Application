package com.self.dto;

import com.self.pojo.DocumentCodeInfo;

import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
public class Codes {
    private List<DocumentCodeInfo> suggestedCode;
    private List<DocumentCodeInfo> acceptedCode;
    private List<DocumentCodeInfo> rejectedCode;
    private String fileId;

    public List<DocumentCodeInfo> getSuggestedCode() {
        return suggestedCode;
    }

    public void setSuggestedCode(List<DocumentCodeInfo> suggestedCode) {
        this.suggestedCode = suggestedCode;
    }

    public List<DocumentCodeInfo> getAcceptedCode() {
        return acceptedCode;
    }

    public void setAcceptedCode(List<DocumentCodeInfo> acceptedCode) {
        this.acceptedCode = acceptedCode;
    }

    public List<DocumentCodeInfo> getRejectedCode() {
        return rejectedCode;
    }

    public void setRejectedCode(List<DocumentCodeInfo> rejectedCode) {
        this.rejectedCode = rejectedCode;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
