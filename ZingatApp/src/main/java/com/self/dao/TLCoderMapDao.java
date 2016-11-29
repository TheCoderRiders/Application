package com.self.dao;

import com.self.models.AllocaterTLMapEntity;
import com.self.models.TLCoderMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by akash.p on 28/11/16.
 */
public interface TLCoderMapDao extends JpaRepository<TLCoderMapEntity, Long> {

    public List<TLCoderMapEntity> getCoderIdByTlId(Integer tlId);
}
