package com.self.enums;

/**
 * Created by akash.p on 26/9/16.
 */
public enum FileStatus {

    DRAFT(555,"Draft"),
    REJECTED(222,"Rejected"),
    SUBMIT(666,"Coding Completed");
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
