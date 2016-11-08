package com.self.dto;

import com.self.pojo.ActualCode;

import java.util.List;

/**
 * Created by akash.p on 10/9/16.
 */
public class CodeAction {

    private Codes allCodes;
    private String sectionName;
    private ActualCode code;
    private String action;
    private String codeActionType;
    private String dos;
    private String sign;

    public Codes getAllCodes() {
        return allCodes;
    }

    public void setAllCodes(Codes allCodes) {
        this.allCodes = allCodes;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public ActualCode getCode() {
        return code;
    }

    public void setCode(ActualCode code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCodeActionType() {
        return codeActionType;
    }

    public void setCodeActionType(String codeActionType) {
        this.codeActionType = codeActionType;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
