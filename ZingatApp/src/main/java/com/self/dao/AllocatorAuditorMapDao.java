package com.self.dao;

import com.self.models.AllocatorAuditorMapEntity;
import com.self.models.AuditorCoderMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akash.p on 28/11/16.
 */
public interface AllocatorAuditorMapDao extends JpaRepository<AllocatorAuditorMapEntity, Long> {

    public List<Integer> getAllocatorIdByAuditorId(String auditorId);
}
