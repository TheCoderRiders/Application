package com.self.service;

import com.self.dao.DocumentMasterDao;
import com.self.dao.RoleMasterDao;
import com.self.dto.Bucket;
import com.self.dto.FileDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */

@Service
public class WorklistServiceImpl implements WorklistService {

    @Autowired
    private DocumentMasterDao documentMasterDao;

    @Autowired
    private RoleMasterDao roleMasterDao;

    @Override
    public List<Bucket> getBucketsInfo(String role) {
        return documentMasterDao.getBucketInfo(role);
    }

    @Override
    public List<String> getAllRoles() {
        return roleMasterDao.getAllRole();
    }

    @Override
    public List<FileDetails> getFileDetails(String bucketName, List<String> currentRole, String orderBy, boolean isAsc, int pageNumber) {

        Sort.Direction sortDirection = Sort.Direction.DESC;
        if(isAsc) sortDirection = Sort.Direction.ASC;

        Sort sort = new Sort(sortDirection,orderBy);
        Pageable pageable = new PageRequest(pageNumber-1,20,sort);
        return documentMasterDao.getFileDetails(bucketName,currentRole,pageable);
    }

    @Override
    public String getFileContents(String fileId) {
        return documentMasterDao.getFileContents(fileId);
    }

    @Override
    public List<Bucket> getBucketsInfo(String role, int userId) {
        return documentMasterDao.getBucketInfoByUserId(role, String.valueOf(userId));
    }

    @Override
    public List<FileDetails> getFileDetailsByUserId(String bucketName, List<String> currentRole, int userId, String orderBy, boolean isAsc, int pageNumber) {
        Sort.Direction sortDirection = Sort.Direction.DESC;
        if(isAsc) sortDirection = Sort.Direction.ASC;

        Sort sort = new Sort(sortDirection,orderBy);
        Pageable pageable = new PageRequest(pageNumber-1,20,sort);
        return documentMasterDao.getFileDetailsByUserId(bucketName,currentRole,String.valueOf(userId),pageable);
    }
}
