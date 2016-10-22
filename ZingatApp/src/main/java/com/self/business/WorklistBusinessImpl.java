package com.self.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.dao.DocumentMasterDao;
import com.self.dao.RoleBucketRightSideMapDao;
import com.self.dao.UserMasterDao;
import com.self.dto.*;
import com.self.enums.Action;
import com.self.enums.Role;
import com.self.enums.SortingParameters;
import com.self.models.*;
import com.self.service.WorklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
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

    @Autowired
    private RoleBucketRightSideMapDao roleBucketRightSideMapDao;

    private List<Integer> assignedFileStatusIds = Arrays.asList(444,555,666);

    private List<Integer> assignedToTLFileStatusIds = Arrays.asList(333);

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
    public FileDetailsResponse getFileDetails(String bucketName, String currentRole, int userId, String orderBy, boolean isAsc, int pageNumber) {
        //String role = getCurrentRole(currentRole);
        if(currentRole.equalsIgnoreCase("Coder") /*|| bucketName.equalsIgnoreCase("New") || bucketName.equalsIgnoreCase("Draft")*/){
            return worklistService.getFileDetailsByUserId(bucketName, Role.getRoles(currentRole),userId,orderBy,isAsc,pageNumber);
        }
        FileDetailsResponse fileDetailsResponse = worklistService.getFileDetails(bucketName, Role.getRoles(currentRole), orderBy, isAsc, pageNumber);
        fileDetailsResponse.getFileDetailsList().parallelStream()
        .filter(fileDetail -> !(assignedFileStatusIds.contains(fileDetail.getFileStatusId().intValue()) || (("Allocater").equalsIgnoreCase(currentRole) && assignedToTLFileStatusIds.contains(fileDetail.getFileStatusId().intValue()))))
        .forEach(fileDetail -> fileDetail.setCheckBoxVisible(true));

        return fileDetailsResponse;
    }

    @Override
    public FileContent getFileContents(String fileId, String currentRole) {
        //String fileContents = worklistService.getFileContents(fileId);
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);
        String fileContents =documentMasterEntity.getDocumentContents();
        String fileStatus = documentMasterEntity.getDocumentCurrentStatus();
        DocRejectionReasonListEntity docRejectionReasonListEntity = null;
        String documentRejectionReason = documentMasterEntity.getDocumentRejectionReason();
        if (documentRejectionReason !=null && !documentRejectionReason.isEmpty())
        try {
            docRejectionReasonListEntity = new ObjectMapper().readValue(documentRejectionReason,DocRejectionReasonListEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileMode = "View";
        if(currentRole.toLowerCase().contains("coder") && ("Allocate to Coder".equalsIgnoreCase(fileStatus) || "Draft".equalsIgnoreCase(fileStatus))){
            fileMode = "Edit";
        }

        FileContent fileContent = new FileContent();
        fileContent.setData(fileContents);
        fileContent.setFileMode(fileMode);
        fileContent.setDocumentRejectionReason(docRejectionReasonListEntity);
        return fileContent;
    }

    @Override
    public List<UserBasicInfo> getUsersForAllocation(String actionId) {
        List<String> roleList = Action.getRoleByAction(actionId);
        List<UserMasterEntity> users = userMasterDao.findByRoleNameIn(roleList);
        return Arrays.asList( new ObjectMapper().convertValue(users, UserBasicInfo[].class));
    }

    @Override
    public Boolean assignedTo(DocAssignInfo docAssignInfo, UserMasterEntity userInfo) {
        List<DocumentMasterEntity> documentMasterEntityList = documentMasterDao.findByDocumentIdIn(docAssignInfo.getFileId());
        StatusMasterEntity statusMasterEntity = Action.getAssignedStatusByAction(docAssignInfo.getActionId());
        documentMasterEntityList.forEach(documentMasterEntity ->{
            documentMasterEntity.setDocumentCurrentStatus(statusMasterEntity.getStatusValue());
            documentMasterEntity.setDocumentCurrentStatusId(statusMasterEntity.getStatusId());
            documentMasterEntity.setDocumentAssignedDatetime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            documentMasterEntity.setDocumentAssignedId(docAssignInfo.getAssignedUserId());
            documentMasterEntity.setDocumentAssignedName(docAssignInfo.getAssignedUserName());
            documentMasterEntity.setDocumentAssigneeId(String.valueOf(userInfo.getUserId()));
            documentMasterEntity.setDocumentAssigneeName(userInfo.getUsername());
        });

        documentMasterDao.save(documentMasterEntityList);

        return true;
    }

    @Override
    public List<RightSideColumnResponse> getRightSideColumns(String fileId, int roleId, String bucketName) {
        List<RoleBucketRightsideMapEntity> roleBucketRightsideMapEntities = roleBucketRightSideMapDao.findByRoleIdAndBucketValue(roleId, bucketName);
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);
        List<RightSideColumnResponse> rightSideColumnResponseList = new ArrayList<>();
        roleBucketRightsideMapEntities.forEach(valueMap->{
            RightSideColumnResponse rightSideColumnResponse = new RightSideColumnResponse();
            rightSideColumnResponse.setColumnName(valueMap.getRightsideColumnKey());
            rightSideColumnResponse.setColumnSequenceNumber(valueMap.getRightsideColumnSequence());

            try {
                Field declaredField = documentMasterEntity.getClass().getDeclaredField(getClassVariableName(valueMap.getRightsideColumnName()));
                declaredField.setAccessible(true);
                Object value = declaredField.get(documentMasterEntity);
                rightSideColumnResponse.setColumnValue(value==null?null:value.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            rightSideColumnResponseList.add(rightSideColumnResponse);
        });

        return rightSideColumnResponseList;
    }

    private String getClassVariableName(String rightsideColumnKey) {
        String[] variableNameArray = rightsideColumnKey.split("_");
        String variableName="";
        for (int i=0;i<variableNameArray.length;i++){
            String var = variableNameArray[i];
            if (i==0){
                variableName+=var;
            }else {
                variableName += (char) (var.charAt(0) - 32) + var.substring(1, var.length());
            }
        }
        return variableName;
    }

    /*@Override
    public List<AvailableOption> getSortParameters(String currentRole) {

        for(SortingParameters sortingParameters:SortingParameters.class.getEnumConstants(){

        }

        return null;
    }*/
}
