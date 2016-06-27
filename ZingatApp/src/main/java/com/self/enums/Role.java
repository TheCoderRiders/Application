package com.self.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */
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
}
