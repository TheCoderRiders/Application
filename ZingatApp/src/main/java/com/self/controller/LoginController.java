package com.self.controller;

import com.self.business.ProfilePageBusiness;
import com.self.dto.UserBasicInfo;
import com.self.models.UserMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.security.Principal;

/**
 * Created by akash.p on 4/6/16.
 */
@RestController
public class LoginController {

    @Autowired
    private ProfilePageBusiness profilePageBusiness;

    @RequestMapping("/user")
    public UserBasicInfo user(Principal user,HttpSession session) {
        session.setAttribute("user",user);
        String username = ((User) ((UsernamePasswordAuthenticationToken) user).getPrincipal()).getUsername();
        UserMasterEntity profile = profilePageBusiness.getProfile(username);
        session.setAttribute("userInfo", profile);
        UserBasicInfo userBasicInfo = new UserBasicInfo();
        userBasicInfo.setUsername(profile.getUsername());
        userBasicInfo.setUserId(profile.getUserId());
        userBasicInfo.setClientName(profile.getClientName());
        return userBasicInfo;
    }

    @RequestMapping("/logout")
    public boolean logout(Principal user,HttpSession session) {
        session.invalidate();
        return true;
    }
}
