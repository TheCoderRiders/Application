package com.self.controller;

import com.self.business.WorklistBusiness;
import com.self.dao.DocumentMasterDao;
import com.self.dto.BucketActions;
import com.self.dto.FileDetails;
import com.self.models.DocumentMasterEntity;
import com.self.models.RoleBucketStatusMapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.p on 14/6/16.
 */
@RestController
@RequestMapping("worklistPage")
public class WorklistController extends BaseController {

    @Autowired
    private WorklistBusiness worklistBusiness;

    @RequestMapping("/bucketInfo")
    public BucketActions getDocumentInformation(Principal user){
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) user).getAuthorities();
        Object[] roleNames = authorities.toArray();

        return worklistBusiness.getBucketsAndActions(roleNames[0].toString());
    }

    @RequestMapping("/getFileDetails")
    public List<FileDetails> getFileDetails(String bucketName,String orderBy,int pageNumber,HttpSession session){
        String currentRole = getCurrentRole(session);
        return worklistBusiness.getFileDetails(bucketName,currentRole,orderBy,pageNumber);
    }

    @RequestMapping("/getFileContents")
    public String getFileContents(String fileId){
        return worklistBusiness.getFileContents(fileId);
    }

    private String getCurrentRole(HttpSession session) {
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) session.getAttribute("user")).getAuthorities();
        Object[] roleNames = authorities.toArray();
        return roleNames[0].toString();
    }

    @RequestMapping("/getSortParameters")
    public String getSortParameters(HttpSession session){
        String currentRole = getCurrentRole(session);
        return worklistBusiness.getSortParameters(currentRole);
    }

}
