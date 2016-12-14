package com.self.enums;

/**
 * Created by akash.p on 26/9/16.
 */
public enum FileStatus {

    DRAFT(555,"Draft"),
    REJECTED(222,"Rejected"),
    SUBMIT(666,"Coding Completed"),
    DOUBT(999,"Doubt"),
    REBUTTAL(3333,"Rebuttal"),
    SENT_TO_CLIENT(777,"Sent To Client"),
    RESOLVED_DOUBT(1111,"Resolved Doubt"),
    NEEDS_REBUTTAL_CLARIFICATION(4444,"Needs Rebuttal Clarification"),
    REBUTTAL_CLARIFICATION(5555,"Rebuttal Clarification"),
    REWORK(2222,"Rework")
    ;
    private Integer id;
    private String status;

    FileStatus(Integer id,String status){
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
