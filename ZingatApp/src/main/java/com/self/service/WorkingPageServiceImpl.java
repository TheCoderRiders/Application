package com.self.service;

import com.self.dao.DocumentMasterDao;
import com.self.models.DocumentMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.p on 16/6/16.
 */

@Service
public class WorkingPageServiceImpl implements WorkingPageService {

    @Autowired
    DocumentMasterDao documentMasterDao;

    @Override
    public DocumentMasterEntity getCodes(String fileId) {
        return documentMasterDao.findByDocumentId(fileId);
    }
}
