package com.self.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.self.constants.Constants;

import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentCodeInfo implements Comparable<DocumentCodeInfo> {

    private String sectionName;
    private List<ActualCode> codes;
    private String dos;
    private String sign;
    private String signPresent;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<ActualCode> getCodes() {
        return codes;
    }

    public void setCodes(List<ActualCode> codes) {
        this.codes = codes;
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
        if(sign!=null && !sign.isEmpty()){
            setSignPresent(Constants.SIGN_PRESENT);
        }else {
            setSignPresent(Constants.SIGN_NOT_PRESENT);
        }
    }

    @Override
    public int compareTo(DocumentCodeInfo o) {
        if(sectionName==null) return 0;
        return sectionName.compareTo(o.getSectionName());
    }

    public String getSignPresent() {
        return signPresent;
    }

    public void setSignPresent(String signPresent) {
        this.signPresent = signPresent;
    }
}
