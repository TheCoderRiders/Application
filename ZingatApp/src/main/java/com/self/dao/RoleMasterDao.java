package com.self.dao;

import com.self.models.RoleMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */

@Transactional
public interface RoleMasterDao extends JpaRepository<RoleMasterEntity, Long> {

    @Query("select roleName from RoleMasterEntity")
    public List<String> getAllRole();

}
