package com.self.service;

import com.self.dto.Bucket;
import com.self.dto.Codes;
import com.self.dto.FileDetails;
import com.self.models.DocumentMasterEntity;

import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */
public interface WorkingPageService {
    public DocumentMasterEntity getCodes(String fileId);
}
