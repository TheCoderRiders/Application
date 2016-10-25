package com.self.dao;

import com.self.models.CodeRejectionReasonListEntity;
import com.self.models.RolewiseDefaultBucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akash.p on 14/10/16.
 */
public interface RolewiseDefaultBucketDao extends JpaRepository<RolewiseDefaultBucketEntity, Long> {

    public RolewiseDefaultBucketEntity findByRoleId(Integer roleId);
}
