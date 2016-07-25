package com.self.dto;

import com.self.pojo.CodeInfo;

import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
public class Codes {
    private List<CodeInfo> suggestedCode;
    private List<CodeInfo> acceptedCode;
    private List<CodeInfo> rejectedCode;

    public List<CodeInfo> getSuggestedCode() {
        return suggestedCode;
    }

    public void setSuggestedCode(List<CodeInfo> suggestedCode) {
        this.suggestedCode = suggestedCode;
    }

    public List<CodeInfo> getAcceptedCode() {
        return acceptedCode;
    }

    public void setAcceptedCode(List<CodeInfo> acceptedCode) {
        this.acceptedCode = acceptedCode;
    }

    public List<CodeInfo> getRejectedCode() {
        return rejectedCode;
    }

    public void setRejectedCode(List<CodeInfo> rejectedCode) {
        this.rejectedCode = rejectedCode;
    }
}
