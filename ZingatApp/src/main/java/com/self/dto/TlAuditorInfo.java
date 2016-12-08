package com.self.dto;

import com.self.constants.Constants;

/**
 * Created by akash.p on 8/12/16.
 */
public class TlAuditorInfo {

    private int userId;
    private String name;

    public TlAuditorInfo(int userId, String firstName, String middleName, String lastName) {
        this.userId = userId;
        StringBuffer stringBuffer = new StringBuffer(Constants.EMPTY);
        if(null != firstName && !firstName.isEmpty()){
            stringBuffer.append(firstName);
        }
        if(null != middleName && !middleName.isEmpty()){
            stringBuffer.append(Constants.SPACE);
            stringBuffer.append(middleName);
        }
        if(null != lastName && !lastName.isEmpty()){
            stringBuffer.append(Constants.SPACE);
            stringBuffer.append(lastName);
        }
        this.name = stringBuffer.toString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
