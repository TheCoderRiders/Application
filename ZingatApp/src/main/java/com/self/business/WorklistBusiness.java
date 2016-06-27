package com.self.business;

import com.self.dto.BucketActions;
import com.self.dto.FileDetails;

import java.util.List;

/**
 * Created by akash.p on 17/6/16.
 */
public interface WorklistBusiness {

    public BucketActions getBucketsAndActions(String role);

    public List<FileDetails> getFileDetails(String bucketName, String currentRole);

    public String getFileContents(String fileId);
}
