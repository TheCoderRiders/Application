package com.self.dao;

import com.self.models.AllocaterTLMapEntity;
import com.self.models.AuditorCoderMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akash.p on 28/11/16.
 */
public interface AuditorCoderMapDao extends JpaRepository<AuditorCoderMapEntity, Long> {

    public List<AuditorCoderMapEntity> getAuditorIdByCoderId(Integer coderId);

    public List<AuditorCoderMapEntity> findByAuditorId(Integer auditorId);
}
