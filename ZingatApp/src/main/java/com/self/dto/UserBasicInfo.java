package com.self.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by akash.p on 28/8/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasicInfo {

    private String username;

    private int userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
