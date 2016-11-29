package com.self.dao;

import com.self.models.AllocaterTLMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akash.p on 28/11/16.
 */
public interface AllocatorTLMapDao extends JpaRepository<AllocaterTLMapEntity, Long> {

    public List<AllocaterTLMapEntity> getTlIdByAllocatorId(Integer allocatorId);
}
