package com.self.dto;

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

    public FileDetails(String fileName, String fileId, String receivedDate, String assigneeName, String fileStatus, String statusClass, Boolean checkBoxVisible) {
        this.fileName = fileName;
        this.fileId = fileId;
        this.receivedDate = receivedDate;
        this.assigneeName = assigneeName;
        this.fileStatus = fileStatus;
        this.statusClass = statusClass;
        this.checkBoxVisible = checkBoxVisible;
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
