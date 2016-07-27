package com.self.business;

import com.self.dto.Codes;
import com.self.dto.UserProfileInformation;
import com.self.models.UserMasterEntity;

import java.io.IOException;

/**
 * Created by akash.p on 25/7/16.
 */
public interface ProfilePageBusiness {
    public UserProfileInformation getProfile(UserMasterEntity userInfo) throws IOException;

    public UserMasterEntity saveProfile(UserMasterEntity userInfo, UserProfileInformation userProfileInformation);

    public UserMasterEntity getProfile(String username);
}
