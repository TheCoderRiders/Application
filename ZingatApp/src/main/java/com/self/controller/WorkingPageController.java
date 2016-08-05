package com.self.controller;

import com.self.business.WorkingPageBusiness;
import com.self.business.WorklistBusiness;
import com.self.dto.BucketActions;
import com.self.dto.Codes;
import com.self.dto.UserProfileInformation;
import com.self.pojo.StringClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by akash.p on 25/7/16.
 */
@RestController
@RequestMapping("workingPage")
public class WorkingPageController extends BaseController {

    @Autowired
    private WorkingPageBusiness workingPageBusiness;

    @RequestMapping(value = "/getCodes")
    public Codes getCodes(String fileId) throws IOException {
        return workingPageBusiness.getCodes(fileId);
    }
}