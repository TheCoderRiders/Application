package com.self.dto;

import com.self.enums.FileType;

import java.util.List;

/**
 * Created by akash.p on 16/12/16.
 */
public class DownloadZipInput {

    private List<String> fileIds;
    private FileType fileType;

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
