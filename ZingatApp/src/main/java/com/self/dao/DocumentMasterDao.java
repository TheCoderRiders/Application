package com.self.dao;

import com.self.dto.Bucket;
import com.self.dto.FileDetails;
import com.self.models.DocumentMasterEntity;
import com.self.models.RoleBucketStatusMapEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * Created by akash.p on 14/6/16.
 */

@Transactional
public interface DocumentMasterDao extends JpaRepository<DocumentMasterEntity, Long> , BaseDao {

    public List<DocumentMasterEntity> findAll();

    @Query("select bucket.bucketValue as bucketName, count(bucket.bucketValue) as bucketCount from DocumentMasterEntity document " +
            ",RoleBucketStatusMapEntity bucket where document.documentCurrentStatusId = bucket.statusId " +
            " and bucket.roleName =:roleName group by bucket.bucketValue")
    public List<Bucket> getBucketInfo(@Param("roleName") String roles);

    @Query("select document.documentId,document.documentName,document.documentRecivedDatetime,document.documentAssigneeName," +
            "document.documentCurrentStatus,roleMap.statusCssClass from DocumentMasterEntity as document,RoleBucketStatusMapEntity as roleMap where " +
            "document.documentCurrentStatusId = roleMap.statusId and roleMap.bucketValue =:bucketName and roleMap.roleName=:currentRole")
    public List<FileDetails> getFileDetails(@Param("bucketName") String bucketName,
                                      @Param("currentRole") String currentRole);

    @Query("select documentContents from DocumentMasterEntity where documentId=:fileId")
    public String getFileContents(@Param("fileId") String fileId);

    /*@Query(GET_BUCKET_INFO)
    public List<Bucket> getBucketInfo(@Param("roleName") String roles);

    @Query(GET_FILE_DETAILS)
    public FileDetails getFileDetails(@Param("bucketName") String bucketName,
                                      @Param("currentRole") String currentRole);*/
}
