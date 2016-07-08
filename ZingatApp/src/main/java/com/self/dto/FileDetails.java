package com.self.dto;

import java.util.Date;

/**
 * Created by akash.p on 23/6/16.
 */
public class FileDetails {

    private String fileName;
    private String fileId;
    private String receivedDate;
    private String assigneeName;
    private String fileStatus;
    private String statusClass;
    private Boolean checkBoxVisible;


    public FileDetails(){

    }

    public FileDetails(String fileName, String fileId, Date receivedDate, String assigneeName, String fileStatus, String statusClass) {
        this.fileName = fileName;
        this.fileId = fileId;
        if(receivedDate==null) {
            this.receivedDate = receivedDate.toString();
        }
        this.assigneeName = assigneeName;
        this.fileStatus = fileStatus;
        this.statusClass = statusClass;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getStatusClass() {
        return statusClass;
    }

    public void setStatusClass(String statusClass) {
        this.statusClass = statusClass;
    }

    public Boolean getCheckBoxVisible() {
        return checkBoxVisible;
    }

    public void setCheckBoxVisible(Boolean checkBoxVisible) {
        this.checkBoxVisible = checkBoxVisible;
    }
}
