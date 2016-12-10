package com.self.dto;

import com.self.constants.Constants;

/**
 * Created by akash.p on 8/12/16.
 */
public class TlAuditorInfo {

    private int userId;
    private String name;
    private String userName;

    public TlAuditorInfo(int userId, String firstName, String middleName, String lastName,String userName) {
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
        this.userName=userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
