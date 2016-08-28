package com.self.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.dao.DocumentMasterDao;
import com.self.dao.UserMasterDao;
import com.self.dto.*;
import com.self.enums.Action;
import com.self.enums.Role;
import com.self.enums.SortingParameters;
import com.self.models.DocumentMasterEntity;
import com.self.models.StatusMasterEntity;
import com.self.models.UserMasterEntity;
import com.self.service.WorklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by akash.p on 17/6/16.
 */
@Component
public class WorklistBusinessImpl implements WorklistBusiness{

    @Autowired
    private WorklistService worklistService;

    @Autowired
    private UserMasterDao userMasterDao;

    @Autowired
    private DocumentMasterDao documentMasterDao;

    @Override
    public BucketActions getBucketsAndActions(String role, int userId) {
        List<Bucket> bucketsInfo = worklistService.getBucketsInfo(role,userId);
        if(role.equalsIgnoreCase("TL_Allocater") || role.equalsIgnoreCase("TL_Allocater_Coder")|| role.equalsIgnoreCase("Allocater")) {
            List<Bucket> additionalBuckets = worklistService.getBucketsInfo("Allocater");
            /*bucketsInfo.forEach(bucket -> {
                Bucket add = additionalBuckets.remove(additionalBuckets.indexOf(bucket));
                bucket.setBucketCount(add.getBucketCount());
            });*/
            bucketsInfo.removeAll(additionalBuckets);
            bucketsInfo.addAll(additionalBuckets);
        }

        Collections.sort(bucketsInfo);
        Set<AvailableOption> availableOptions = new LinkedHashSet<AvailableOption>();

        BucketActions bucketActions = new BucketActions();
        bucketActions.setBuckets(bucketsInfo);
        Actions actions = new Actions();

        List<String> allRoles = worklistService.getAllRoles();

        for (String userRole : allRoles){
            if (userRole.equalsIgnoreCase(role)) continue;

            Role actualRoleEnum = Role.valueOf(role);
            List<Action> actionList = actualRoleEnum.getActions();
            for(int i =0 ; i<actionList.size() ; i++) {
                Action action = actionList.get(i);
                AvailableOption availableOption = new AvailableOption(action.getId(),action.getDisplayValue());
                if(i==0 && actions.getSelectedOption()==null){
                    actions.setSelectedOption(availableOption);
                }//else if (!actions.getSelectedOption().equals(availableOption)){
                    availableOptions.add(availableOption);
                //}
            }
        }

        actions.setAvailableOptions(new ArrayList<AvailableOption>(availableOptions));

        bucketActions.setActions(actions);

        //sort
        Actions sortObject = new Actions();
        Set<AvailableOption> sortList = new LinkedHashSet<AvailableOption>();
        SortingParameters[] sortingParameters = SortingParameters.class.getEnumConstants();
        for(int i =0 ; i<sortingParameters.length ; i++) {
            SortingParameters parameters = sortingParameters[i];
            AvailableOption availableOption = new AvailableOption(parameters.getKey(),parameters.getValue());
            if(i==0 && sortObject.getSelectedOption()==null){
                sortObject.setSelectedOption(availableOption);
            }//else if (!sortObject.getSelectedOption().equals(availableOption)){
                sortList.add(availableOption);
            //}
        }
        sortObject.setAvailableOptions(new ArrayList<AvailableOption>(sortList));
        bucketActions.setSortParams(sortObject);

        return bucketActions;
    }

    @Override
    public List<FileDetails> getFileDetails(String bucketName, String currentRole, int userId, String orderBy, boolean isAsc, int pageNumber) {
        if(currentRole.equalsIgnoreCase("Coder") || bucketName.equalsIgnoreCase("New") || bucketName.equalsIgnoreCase("Draft")){
            worklistService.getFileDetailsByUserId(bucketName, currentRole,userId,orderBy,isAsc,pageNumber);
        }
       return worklistService.getFileDetails(bucketName, currentRole,orderBy,isAsc,pageNumber);
    }

    @Override
    public String getFileContents(String fileId) {
        return worklistService.getFileContents(fileId);
    }

    @Override
    public List<UserBasicInfo> getUsersForAllocation(String actionId) {
        List<String> roleList = Action.getRoleByAction(actionId);
        List<UserMasterEntity> users = userMasterDao.findByRoleNameIn(roleList);
        return Arrays.asList( new ObjectMapper().convertValue(users, UserBasicInfo[].class));
    }

    @Override
    public Boolean assignedTo(DocAssignInfo docAssignInfo, UserMasterEntity userInfo) {
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(docAssignInfo.getFileId());
        StatusMasterEntity statusMasterEntity = Action.getAssignedStatusByAction(docAssignInfo.getActionId());

        documentMasterEntity.setDocumentCurrentStatus(statusMasterEntity.getStatusValue());
        documentMasterEntity.setDocumentCurrentStatusId(statusMasterEntity.getStatusId());
        documentMasterEntity.setDocumentAssignedDatetime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        documentMasterEntity.setDocumentAssignedId(docAssignInfo.getAssignedUserId());
        documentMasterEntity.setDocumentAssignedName(docAssignInfo.getAssignedUserName());
        documentMasterEntity.setDocumentAssigneeId(String.valueOf(userInfo.getUserId()));
        documentMasterEntity.setDocumentAssigneeName(userInfo.getUsername());

        documentMasterDao.save(documentMasterEntity);

        return true;
    }

    /*@Override
    public List<AvailableOption> getSortParameters(String currentRole) {

        for(SortingParameters sortingParameters:SortingParameters.class.getEnumConstants(){

        }

        return null;
    }*/
}
