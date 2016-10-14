package com.self.controller;

import com.self.business.WorkingPageBusiness;
import com.self.business.WorklistBusiness;
import com.self.dto.*;
import com.self.enums.FileStatus;
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
    public Codes getCodes(String fileId) throws IOException {
        return workingPageBusiness.getCodes(fileId);
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
    public Boolean documentStatusChange(String fileId,FileStatus status) throws IOException {
        return workingPageBusiness.documentStatusChange(fileId, status);
    }

    @RequestMapping(value = "/getDocRejectionReasonList")
    public List getDocRejectionReasonList() {
        return workingPageBusiness.getDocRejectionReasonList();
    }

    @RequestMapping(value = "/getCodeRejectionReasonList")
    public List getCodeRejectionReasonList() {
        return workingPageBusiness.getCodeRejectionReasonList();
    }
}
