package com.self.dao;

import com.self.dto.UserProfileInformation;
import com.self.models.UserMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by akash.p on 16/6/16.
 */

@Transactional
public interface UserMasterDao extends JpaRepository<UserMasterEntity, Long> {

    public UserMasterEntity findByUserId(Integer id);

    public UserMasterEntity findByusername(String userName);

    public List<UserMasterEntity> findByRoleNameIn(List<String> roleName);

    public List<UserMasterEntity> findByUserIdIn(List<Integer> idList);
}
