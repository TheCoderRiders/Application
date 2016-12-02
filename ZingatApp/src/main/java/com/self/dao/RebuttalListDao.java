package com.self.dao;

import com.self.models.DoubtListEntity;
import com.self.models.RebuttalListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by akash.p on 14/10/16.
 */
public interface RebuttalListDao extends JpaRepository<RebuttalListEntity, Long> {
}
