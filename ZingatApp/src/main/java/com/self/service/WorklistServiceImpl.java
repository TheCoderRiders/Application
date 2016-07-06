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
    DocumentMasterDao documentMasterDao;

    @Autowired
    RoleMasterDao roleMasterDao;

    @Override
    public List<Bucket> getBucketsInfo(String role) {
        return documentMasterDao.getBucketInfo(role);
    }

    @Override
    public List<String> getAllRoles() {
        return roleMasterDao.getAllRole();
    }

    @Override
    public List<FileDetails> getFileDetails(String bucketName, String currentRole, String orderBy,int pageNumber) {
        Sort sort = new Sort(Sort.Direction.ASC,orderBy);
        Pageable pageable = new PageRequest(pageNumber-1,20,sort);
        return documentMasterDao.getFileDetails(bucketName,currentRole,pageable);
    }

    @Override
    public String getFileContents(String fileId) {
        return documentMasterDao.getFileContents(fileId);
    }
}
