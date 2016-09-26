package com.self.dao;

import com.self.dto.Bucket;
import com.self.dto.Codes;
import com.self.dto.FileDetails;
import com.self.models.DocumentMasterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by akash.p on 14/6/16.
 */

@Transactional
public interface DocumentMasterDao extends JpaRepository<DocumentMasterEntity, Long> , BaseDao {

    public List<DocumentMasterEntity> findAll();

    @Query(value = "select NEW com.self.dto.Bucket( bucket.bucketValue, count(bucket.bucketValue) ) from DocumentMasterEntity document " +
            ",RoleBucketStatusMapEntity bucket where document.documentCurrentStatusId = bucket.statusId " +
            " and bucket.roleName =:roleName group by bucket.bucketValue")
    public List<Bucket> getBucketInfo(@Param("roleName") String roles);


    @Query(value = "select NEW com.self.dto.Bucket( bucket.bucketValue, count(bucket.bucketValue) ) from DocumentMasterEntity document " +
            ",RoleBucketStatusMapEntity bucket where document.documentCurrentStatusId = bucket.statusId " +
            " and bucket.roleName =:roleName and document.documentAssignedId=:documentAssignedId group by bucket.bucketValue")
    public List<Bucket> getBucketInfoByUserId(@Param("roleName") String roles, @Param("documentAssignedId") String documentAssignedId);


    @Query("select new com.self.dto.FileDetails(document.documentName,document.documentId,document.documentRecivedDatetime,document.documentAssigneeName," +
            "document.documentCurrentStatus, document.documentCurrentStatusId, roleMap.statusCssClass) from DocumentMasterEntity as document,RoleBucketStatusMapEntity as roleMap where " +
            "document.documentCurrentStatusId = roleMap.statusId and roleMap.bucketValue =:bucketName and roleMap.roleName in :currentRole " +
            "")
    public List<FileDetails> getFileDetails(@Param("bucketName") String bucketName,
                                      @Param("currentRole") List<String> currentRole,
                                      Pageable pageable);

    @Query("select new com.self.dto.FileDetails(document.documentName,document.documentId,document.documentRecivedDatetime,document.documentAssigneeName," +
            "document.documentCurrentStatus,document.documentCurrentStatusId,roleMap.statusCssClass) from DocumentMasterEntity as document,RoleBucketStatusMapEntity as roleMap where " +
            "document.documentCurrentStatusId = roleMap.statusId and roleMap.bucketValue =:bucketName and roleMap.roleName in :currentRole " +
            "and document.documentAssignedId=:documentAssignedId")
    public List<FileDetails> getFileDetailsByUserId(@Param("bucketName") String bucketName,
                                            @Param("currentRole") List<String> currentRole, @Param("documentAssignedId") String documentAssignedId,
                                            Pageable pageable);


    @Query("select count(*) from DocumentMasterEntity as document,RoleBucketStatusMapEntity as roleMap where " +
            "document.documentCurrentStatusId = roleMap.statusId and roleMap.bucketValue =:bucketName and roleMap.roleName in :currentRole " +
            "")
    public Integer getFileDetailsPageCount(@Param("bucketName") String bucketName,
                                            @Param("currentRole") List<String> currentRole);

    @Query("select count(*) from DocumentMasterEntity as document,RoleBucketStatusMapEntity as roleMap where " +
            "document.documentCurrentStatusId = roleMap.statusId and roleMap.bucketValue =:bucketName and roleMap.roleName in :currentRole " +
            "and document.documentAssignedId=:documentAssignedId")
    public Integer getFileDetailsPageCountByUserId(@Param("bucketName") String bucketName,
                                                    @Param("currentRole") List<String> currentRole, @Param("documentAssignedId") String documentAssignedId);

    @Query("select documentContents from DocumentMasterEntity where documentId=:fileId")
    public String getFileContents(@Param("fileId") String fileId);

    public DocumentMasterEntity findByDocumentId(String fileId);

    /*@Query(GET_BUCKET_INFO)
    public List<Bucket> getBucketInfo(@Param("roleName") String roles);

    @Query(GET_FILE_DETAILS)
    public FileDetails getFileDetails(@Param("bucketName") String bucketName,
                                      @Param("currentRole") String currentRole);*/
}
