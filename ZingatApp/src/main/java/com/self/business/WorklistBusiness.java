package com.self.business;

import com.self.dto.*;
import com.self.models.UserMasterEntity;

import java.util.List;

/**
 * Created by akash.p on 17/6/16.
 */
public interface WorklistBusiness {

    public BucketActions getBucketsAndActions(String role, Integer roleId, Integer userId);

    public FileDetailsResponse getFileDetails(String bucketName, String currentRole, int userId, String orderBy, boolean isAsc, int pageNumber);

    public FileContent getFileContents(String fileId, String currentRole, String page);

    public List<UserBasicInfo> getUsersForAllocation(String actionId, String currentRole, int userId);

    public Boolean assignedTo(DocAssignInfo docAssignInfo, UserMasterEntity userInfo);

    public List<RightSideColumnResponse> getRightSideColumns(String fileId, int roleId, String bucketName);

    /*public List<AvailableOption> getSortParameters(String currentRole);*/
}
