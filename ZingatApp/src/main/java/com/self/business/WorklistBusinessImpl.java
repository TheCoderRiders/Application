package com.self.business;

import com.self.dto.*;
import com.self.enums.SortingParameters;
import com.self.service.WorklistService;
import com.self.enums.Action;
import com.self.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by akash.p on 17/6/16.
 */
@Component
public class WorklistBusinessImpl implements WorklistBusiness{

    @Autowired
    private WorklistService worklistService;

    @Override
    public BucketActions getBucketsAndActions(String role) {
        List<Bucket> bucketsInfo = worklistService.getBucketsInfo(role);
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
    public List<FileDetails> getFileDetails(String bucketName, String currentRole, String orderBy, boolean isAsc, int pageNumber) {
       return worklistService.getFileDetails(bucketName, currentRole,orderBy,isAsc,pageNumber);
    }

    @Override
    public String getFileContents(String fileId) {
        return worklistService.getFileContents(fileId);
    }

    /*@Override
    public List<AvailableOption> getSortParameters(String currentRole) {

        for(SortingParameters sortingParameters:SortingParameters.class.getEnumConstants(){

        }

        return null;
    }*/
}
