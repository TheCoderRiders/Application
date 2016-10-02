package com.self.business;

import com.self.dto.*;
import com.self.models.UserMasterEntity;

import java.util.List;

/**
 * Created by akash.p on 17/6/16.
 */
public interface WorklistBusiness {

    public BucketActions getBucketsAndActions(String role, int userId);

    public FileDetailsResponse getFileDetails(String bucketName, String currentRole, int userId, String orderBy, boolean isAsc, int pageNumber);

    public FileContent getFileContents(String fileId, String currentRole);

    public List<UserBasicInfo> getUsersForAllocation(String actionId);

    public Boolean assignedTo(DocAssignInfo docAssignInfo, UserMasterEntity userInfo);

    /*public List<AvailableOption> getSortParameters(String currentRole);*/
}
