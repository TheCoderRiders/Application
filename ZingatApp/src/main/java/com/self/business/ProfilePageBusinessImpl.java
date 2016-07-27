package com.self.business;

import com.self.dao.UserMasterDao;
import com.self.dto.UserProfileInformation;
import com.self.models.UserMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by akash.p on 25/7/16.
 */
@Component
public class ProfilePageBusinessImpl implements ProfilePageBusiness {

    @Autowired
    private UserMasterDao userMasterDao;

    @Override
    public UserProfileInformation getProfile(UserMasterEntity userInfo) throws IOException {
        UserProfileInformation userProfileInformation = new UserProfileInformation();
        userProfileInformation.setAddress1(userInfo.getAddress1());
        userProfileInformation.setAddress2(userInfo.getAddress2());
        userProfileInformation.setCity(userInfo.getCity());
        userProfileInformation.setContactNo(userInfo.getPrimaryPhoneNumber());
        userProfileInformation.setCountry(userInfo.getCountry());
        userProfileInformation.setDob(
                new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(userInfo.getDateOfBirth())
        );
        userProfileInformation.setEmail(userInfo.getEmailId());
        userProfileInformation.setFirstName(userInfo.getFirstName());
        userProfileInformation.setGender(userInfo.getGender());
        userProfileInformation.setLastName(userInfo.getLastName());
        userProfileInformation.setPassword("");
        userProfileInformation.setRepassword("");
        userProfileInformation.setState(userInfo.getState());
        userProfileInformation.setUserName(userInfo.getUsername());
        userProfileInformation.setUserRole(userInfo.getRoleName());
        userProfileInformation.setZipcode(userInfo.getZipcode());

        return userProfileInformation;
    }

    @Override
    public UserMasterEntity saveProfile(UserMasterEntity userInfo, UserProfileInformation userProfileInformation) {
        userInfo.setAddress1(userProfileInformation.getAddress1());
        userInfo.setAddress2(userProfileInformation.getAddress2());
        userInfo.setCity(userProfileInformation.getCity());
        userInfo.setPrimaryPhoneNumber(userProfileInformation.getContactNo());
        userInfo.setCountry(userProfileInformation.getCountry());
        try {
            userInfo.setDateOfBirth(
                    new Date(new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).parse(userProfileInformation.getDob()).getTime())
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userInfo.setEmailId(userProfileInformation.getEmail());
        userInfo.setFirstName(userProfileInformation.getFirstName());
        userInfo.setGender(userProfileInformation.getGender());
        userInfo.setLastName(userProfileInformation.getLastName());
        //userInfo.setPassword("");
        //userInfo.setRepassword("");
        userInfo.setState(userProfileInformation.getState());
        userInfo.setUsername(userProfileInformation.getUserName());
        userInfo.setRoleName(userProfileInformation.getUserRole());
        userInfo.setZipcode(userProfileInformation.getZipcode());
        return userMasterDao.save(userInfo);
    }

    @Override
    public UserMasterEntity getProfile(String username) {
        return userMasterDao.findByusername(username);
    }
}
