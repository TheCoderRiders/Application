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
    private String documentAssignedName;
    private String documentAssigneeName;
    private Date date;
    private boolean commentAck;

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

    public String getDocumentAssignedName() {
        return documentAssignedName;
    }

    public void setDocumentAssignedName(String documentAssignedName) {
        this.documentAssignedName = documentAssignedName;
    }

    public String getDocumentAssigneeName() {
        return documentAssigneeName;
    }

    public void setDocumentAssigneeName(String documentAssigneeName) {
        this.documentAssigneeName = documentAssigneeName;
    }

    public void setCommentAck(boolean commentAck) {
        this.commentAck = commentAck;
    }

    public boolean isCommentAck() {
        return commentAck;
    }
}
