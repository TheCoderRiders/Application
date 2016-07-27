package com.self.controller;

import com.self.business.ProfilePageBusiness;
import com.self.business.WorkingPageBusiness;
import com.self.dto.Codes;
import com.self.dto.UserProfileInformation;
import com.self.models.UserMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by akash.p on 25/7/16.
 */
@RestController
@RequestMapping("profilePage")
public class ProfilePageController extends BaseController {

    @Autowired
    private ProfilePageBusiness profilePageBusiness;

    @RequestMapping(value = "/getProfile")
    public UserProfileInformation getProfile(HttpSession session) throws IOException {
        UserMasterEntity userInfo = (UserMasterEntity) session.getAttribute("userInfo");
        return profilePageBusiness.getProfile(userInfo);
    }

    @RequestMapping(value = "/saveProfile")
    public Boolean saveProfile(@RequestBody UserProfileInformation userProfileInformation,HttpSession session) throws IOException {
        UserMasterEntity userInfo = (UserMasterEntity) session.getAttribute("userInfo");
        UserMasterEntity userMasterEntity = profilePageBusiness.saveProfile(userInfo, userProfileInformation);
        session.setAttribute("userInfo",userMasterEntity);
        return true;
    }
}
