package com.self.enums;

/**
 * Created by akash.p on 17/6/16.
 */
public enum Action {

    ASSIGN_TO_TL("111","Assign to TL"),
    ASSIGN_TO_TL_CODER("222","Assign to TL-Coder"),
    ASSIGN_TO_CODER("333","Assign to Coder");

    private String displayValue;
    private String id;

    Action(String id,String displayValue){
        this.id = id;
        this.displayValue = displayValue;
    }

    public String getId() {
        return id;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
