package com.self.controller;

import com.self.business.WorklistBusiness;
import com.self.dao.DocumentMasterDao;
import com.self.dto.*;
import com.self.enums.Action;
import com.self.models.DocumentMasterEntity;
import com.self.models.RoleBucketStatusMapEntity;
import com.self.models.UserMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BucketActions getDocumentInformation(Principal user ,HttpSession session){
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) user).getAuthorities();
        Object[] roleNames = authorities.toArray();
        UserMasterEntity userInfo = (UserMasterEntity) session.getAttribute("userInfo");
        int userId = userInfo.getUserId();
        return worklistBusiness.getBucketsAndActions(roleNames[0].toString(),userInfo.getRoleId(),userId);
    }

    @RequestMapping("/getFileDetails")
    public FileDetailsResponse getFileDetails(String bucketName,String orderBy,boolean isAsc, int pageNumber ,HttpSession session){
        String currentRole = getCurrentRole(session);
        int userId = ((UserMasterEntity) session.getAttribute("userInfo")).getUserId();
        FileDetailsResponse fileDetails = worklistBusiness.getFileDetails(bucketName, currentRole, userId, orderBy, isAsc, pageNumber);
        session.setAttribute("bucketName",bucketName);
        return fileDetails;
    }

    @RequestMapping("/getFileContents")
    public FileContent getFileContents(String fileId, String page, HttpSession session){
        String currentRole = getCurrentRole(session);
        String bucketName = (String) session.getAttribute("bucketName");
        return worklistBusiness.getFileContents(fileId,currentRole,page,bucketName);
    }

    @RequestMapping("/getUsersForAllocation")
    public List<UserBasicInfo> getUsersForAllocation(String actionId ,HttpSession session){
        String currentRole = getCurrentRole(session);
        int userId = ((UserMasterEntity) session.getAttribute("userInfo")).getUserId();
        return worklistBusiness.getUsersForAllocation(actionId, currentRole, userId);
    }

    @RequestMapping("/assignedTo")
    public Boolean assignedTo(@RequestBody DocAssignInfo docAssignInfo, HttpSession session){
        UserMasterEntity userInfo = (UserMasterEntity) session.getAttribute("userInfo");
        return worklistBusiness.assignedTo(docAssignInfo,userInfo);
    }

    private String getCurrentRole(HttpSession session) {
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) session.getAttribute("user")).getAuthorities();
        Object[] roleNames = authorities.toArray();
        return roleNames[0].toString();
    }

    @RequestMapping(value = "/getRightSideColumns")
    public List<RightSideColumnResponse> getRightSideColumns(String fileId,String bucketName,HttpSession session) {
        UserMasterEntity userInfo = (UserMasterEntity) session.getAttribute("userInfo");
        return worklistBusiness.getRightSideColumns(fileId,userInfo.getRoleId(),bucketName);
    }

    /*@RequestMapping("/getSortParameters")
    public String getSortParameters(HttpSession session){
        String currentRole = getCurrentRole(session);
        return null;// worklistBusiness.getSortParameters(currentRole);
    }*/

}
