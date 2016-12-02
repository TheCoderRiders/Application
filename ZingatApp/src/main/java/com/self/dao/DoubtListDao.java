package com.self.dao;

import com.self.models.DocRejectionReasonListEntity;
import com.self.models.DoubtListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akash.p on 14/10/16.
 */
public interface DoubtListDao extends JpaRepository<DoubtListEntity, Long> {
}
