package com.self.dto;

import com.self.pojo.DocumentCodeInfo;

import java.util.Collections;
import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
public class Codes {
    private List<DocumentCodeInfo> suggestedCode;
    private List<DocumentCodeInfo> acceptedCode;
    private List<DocumentCodeInfo> rejectedCode;
    private List<DocumentCodeInfo> mayBeCode;
    private String fileId;
    private ButtonVisibleInfo buttonVisibleInfo;

    public List<DocumentCodeInfo> getSuggestedCode() {
        return suggestedCode;
    }

    public void setSuggestedCode(List<DocumentCodeInfo> suggestedCode) {
        this.suggestedCode = suggestedCode;
        if(this.suggestedCode!=null)Collections.sort(this.suggestedCode);
    }

    public List<DocumentCodeInfo> getAcceptedCode() {
        return acceptedCode;
    }

    public void setAcceptedCode(List<DocumentCodeInfo> acceptedCode) {
        this.acceptedCode = acceptedCode;
        if(this.acceptedCode!=null)Collections.sort(this.acceptedCode);
    }

    public List<DocumentCodeInfo> getRejectedCode() {
        return rejectedCode;
    }

    public void setRejectedCode(List<DocumentCodeInfo> rejectedCode) {
        this.rejectedCode = rejectedCode;
        if(this.rejectedCode!=null)Collections.sort(this.rejectedCode);
    }

    public List<DocumentCodeInfo> getMayBeCode() {
        return mayBeCode;
    }

    public void setMayBeCode(List<DocumentCodeInfo> mayBeCode) {
        this.mayBeCode = mayBeCode;
        if(this.mayBeCode!=null)Collections.sort(this.mayBeCode);
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public ButtonVisibleInfo getButtonVisibleInfo() {
        return buttonVisibleInfo;
    }

    public void setButtonVisibleInfo(ButtonVisibleInfo buttonVisibleInfo) {
        this.buttonVisibleInfo = buttonVisibleInfo;
    }
}
