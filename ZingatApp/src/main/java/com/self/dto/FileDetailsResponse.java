package com.self.dto;

import java.util.List;

/**
 * Created by akash.p on 26/9/16.
 */
public class FileDetailsResponse {

    List<FileDetails> fileDetailsList;
    Integer pageCount;

    public FileDetailsResponse(List<FileDetails> fileDetails, Integer pageCount) {
        this.fileDetailsList = fileDetails;
        this.pageCount = pageCount;
    }

    public List<FileDetails> getFileDetailsList() {
        return fileDetailsList;
    }

    public void setFileDetailsList(List<FileDetails> fileDetailsList) {
        this.fileDetailsList = fileDetailsList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
