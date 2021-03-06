package com.self.controller;

import com.self.business.WorkingPageBusiness;
import com.self.business.WorklistBusiness;
import com.self.dto.*;
import com.self.enums.FileStatus;
import com.self.models.UserMasterEntity;
import com.self.pojo.ActualCode;
import com.self.pojo.DocumentCodeInfo;
import com.self.pojo.StringClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by akash.p on 25/7/16.
 */
@RestController
@RequestMapping("workingPage")
public class WorkingPageController extends BaseController {

    @Autowired
    private WorkingPageBusiness workingPageBusiness;

    @RequestMapping(value = "/getCodes")
    public Codes getCodes(String fileId, HttpSession session) throws IOException {
        String currentRole = getCurrentRole(session);
        String bucketName = (String) session.getAttribute("bucketName");
        return workingPageBusiness.getCodes(fileId,currentRole,bucketName);
    }

    @RequestMapping(value = "/saveCodes")
    public Boolean saveCodes(@RequestBody Codes codes) throws IOException {
        return workingPageBusiness.saveCodes(codes);
    }

    @RequestMapping(value = "/searchCode")
    public CodeSearchResult searchCode(String key,Integer start){
       return workingPageBusiness.searchCode(key,start);
    }

    @RequestMapping(value = "/codeAction")
    public Codes saveCodes(@RequestBody CodeAction codeAction) throws IOException {
        return workingPageBusiness.codeAction(codeAction);
    }

    @RequestMapping(value = "/documentStatusChange")
    public Boolean documentStatusChange(@RequestBody FileStatusChangeRequest fileStatusChangeRequest, HttpSession session) throws IOException {
        String bucketName = (String) session.getAttribute("bucketName");
        return workingPageBusiness.documentStatusChange(fileStatusChangeRequest,bucketName);
    }

    @RequestMapping(value = "/getDocRejectionReasonList")
    public List getDocRejectionReasonList() {
        return workingPageBusiness.getDocRejectionReasonList();
    }

    @RequestMapping(value = "/getCodeRejectionReasonList")
    public List getCodeRejectionReasonList() {
        return workingPageBusiness.getCodeRejectionReasonList();
    }

    @RequestMapping(value = "/getDoubtList")
    public List getDoubtList() {
        return workingPageBusiness.getDoubtList();
    }

    @RequestMapping(value = "/getRebuttalList")
    public List getRebuttalList() {
        return workingPageBusiness.getRebuttalList();
    }

    @RequestMapping(value = "/acknowledgeComment")
    public Boolean acknowledgeComment(@RequestBody AcknowledgeCommentInfo acknowledgeCommentInfo) throws IOException {
        return workingPageBusiness.acknowledgeComment(acknowledgeCommentInfo);
    }

    @RequestMapping(value = "/getTlList")
    public List getTlList(HttpSession session) throws IOException {
        int userId = ((UserMasterEntity) session.getAttribute("userInfo")).getUserId();
        return workingPageBusiness.getTlList(userId);
    }

    @RequestMapping(value = "/getAuditorList")
    public List getAuditorList(HttpSession session) throws IOException {
        int userId = ((UserMasterEntity) session.getAttribute("userInfo")).getUserId();
        return workingPageBusiness.getAuditorList(userId);
    }

    private String getCurrentRole(HttpSession session) {
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) session.getAttribute("user")).getAuthorities();
        Object[] roleNames = authorities.toArray();
        return roleNames[0].toString();
    }
}
