package com.self.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */
public enum ProductRole {

    Allocator(Action.ASSIGN_TO_TL),
    Auditor(Action.ASSIGN_TO_TL,Action.ASSIGN_TO_TL_CODER),
    TlCoder(Action.ASSIGN_TO_CODER),
    Coder,
    Admin;

    public List<Action> actions = new ArrayList<Action>();

    ProductRole(Action... actions){
        this.actions = Arrays.asList(actions);
    }

    public List<Action> getActions() {
        return actions;
    }
}
