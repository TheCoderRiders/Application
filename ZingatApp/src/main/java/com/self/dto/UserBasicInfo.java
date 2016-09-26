package com.self.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by akash.p on 28/8/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBasicInfo {

    private String username;

    private Integer userId;

    private String clientName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
