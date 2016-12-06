package com.self.dao;

import com.self.models.AcknowledgementDetailsEntity;
import com.self.models.RebuttalListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akash.p on 14/10/16.
 */
public interface AcknowledgementDetailsDao extends JpaRepository<AcknowledgementDetailsEntity, Long> {
}
