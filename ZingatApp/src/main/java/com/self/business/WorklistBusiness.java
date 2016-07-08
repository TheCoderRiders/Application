package com.self.business;

import com.self.dto.AvailableOption;
import com.self.dto.BucketActions;
import com.self.dto.FileDetails;

import java.util.List;

/**
 * Created by akash.p on 17/6/16.
 */
public interface WorklistBusiness {

    public BucketActions getBucketsAndActions(String role);

    public List<FileDetails> getFileDetails(String bucketName, String currentRole, String orderBy, boolean isAsc, int pageNumber);

    public String getFileContents(String fileId);

    /*public List<AvailableOption> getSortParameters(String currentRole);*/
}
