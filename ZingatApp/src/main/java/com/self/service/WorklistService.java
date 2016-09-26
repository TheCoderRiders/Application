package com.self.service;

import com.self.dto.Bucket;
import com.self.dto.FileDetails;
import com.self.dto.FileDetailsResponse;

import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */
public interface WorklistService {

    public List<Bucket> getBucketsInfo(String role);

    public List<String> getAllRoles();

    public FileDetailsResponse getFileDetails(String bucketName, List<String> currentRole, String orderBy, boolean isAsc, int pageNumber);

    public String getFileContents(String fileId);

    public List<Bucket> getBucketsInfo(String role, int userId);

    public FileDetailsResponse getFileDetailsByUserId(String bucketName, List<String> currentRole, int userId, String orderBy, boolean isAsc, int pageNumber);
}
