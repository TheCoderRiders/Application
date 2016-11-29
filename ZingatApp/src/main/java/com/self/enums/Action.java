package com.self.enums;

import com.self.models.StatusMasterEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static List<String> getRoleByAction(String actionId){
        switch (actionId){
            case "111" : return Arrays.asList(ProductRole.TlCoder.name());
            case "222" : return Arrays.asList("TL_Allocater_Coder");
            case "333" : return Arrays.asList(ProductRole.Coder.name());
        }
        return Collections.emptyList();
    }

    public static StatusMasterEntity getAssignedStatusByAction(String actionId){
        switch (actionId){
            case "111" :
            case "222" : return new StatusMasterEntity(333,"Allocate to TLCoder");
            case "333" : return new StatusMasterEntity(444,"Allocate to Coder");
        }
        return new StatusMasterEntity();
    }

}
