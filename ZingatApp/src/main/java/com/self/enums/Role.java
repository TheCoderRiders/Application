/*
package com.self.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

*/
/**
 * Created by akash.p on 16/6/16.
 *//*

public enum Role {

    Allocater(Action.ASSIGN_TO_TL),
    TL_Allocater(Action.ASSIGN_TO_TL,Action.ASSIGN_TO_TL_CODER,Action.ASSIGN_TO_CODER),
    TL_Allocater_Coder(Action.ASSIGN_TO_TL,Action.ASSIGN_TO_TL_CODER,Action.ASSIGN_TO_CODER),
    Coder;

    public List<Action> actions = new ArrayList<Action>();

    Role(Action... actions){
        this.actions = Arrays.asList(actions);
    }

    public List<Action> getActions() {
        return actions;
    }

    public static List<String> getRoles(String role){
        switch (role){
            case "Allocater" : return Arrays.asList("Allocater");
            case "TL_Allocater" : return Arrays.asList("Allocater","TL_Allocater");
            case "TL_Allocater_Coder" : return Arrays.asList("Allocater","TL_Allocater","TL_Allocater_Coder","Coder");
            case "Coder" : return Arrays.asList("Coder");
        }
        return Arrays.asList(role);
    }
}
*/
