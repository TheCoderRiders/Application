package com.self.dto;

import java.util.Date;

/**
 * Created by akash.p on 2/12/16.
 */
public class DoubtRebuttalInfo {

    private int doubtRebuttalId;
    private String doubtRebuttalDisplay;
    private String doubtRebuttalDesc;
    private String doubtRebuttalType;
    private String documentAssignedId;
    private String documentAssigneeId;
    private Date date;

    public int getDoubtRebuttalId() {
        return doubtRebuttalId;
    }

    public void setDoubtRebuttalId(int doubtRebuttalId) {
        this.doubtRebuttalId = doubtRebuttalId;
    }

    public String getDoubtRebuttalDisplay() {
        return doubtRebuttalDisplay;
    }

    public void setDoubtRebuttalDisplay(String doubtRebuttalDisplay) {
        this.doubtRebuttalDisplay = doubtRebuttalDisplay;
    }

    public String getDoubtRebuttalDesc() {
        return doubtRebuttalDesc;
    }

    public void setDoubtRebuttalDesc(String doubtRebuttalDesc) {
        this.doubtRebuttalDesc = doubtRebuttalDesc;
    }

    public String getDoubtRebuttalType() {
        return doubtRebuttalType;
    }

    public void setDoubtRebuttalType(String doubtRebuttalType) {
        this.doubtRebuttalType = doubtRebuttalType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDocumentAssignedId() {
        return documentAssignedId;
    }

    public void setDocumentAssignedId(String documentAssignedId) {
        this.documentAssignedId = documentAssignedId;
    }

    public String getDocumentAssigneeId() {
        return documentAssigneeId;
    }

    public void setDocumentAssigneeId(String documentAssigneeId) {
        this.documentAssigneeId = documentAssigneeId;
    }
}
