package com.self.dto;

import com.self.enums.RebuttalAssign;

/**
 * Created by akash.p on 10/12/16.
 */
public class RebuttalActionInfo {

    private RebuttalAssign rebuttalAssign;
    private String assignedId;
    private String assignedUserName;

    public RebuttalAssign getRebuttalAssign() {
        return rebuttalAssign;
    }

    public void setRebuttalAssign(RebuttalAssign rebuttalAssign) {
        this.rebuttalAssign = rebuttalAssign;
    }

    public String getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(String assignedId) {
        this.assignedId = assignedId;
    }

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }
}
