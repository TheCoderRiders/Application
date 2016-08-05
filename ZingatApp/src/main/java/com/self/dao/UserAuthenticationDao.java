package com.self.dao;

import com.self.models.UserAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by akash.p on 4/8/16.
 */
@Transactional
public interface UserAuthenticationDao extends JpaRepository<UserAuthenticationEntity, Long> {

    public UserAuthenticationEntity findOneByUsername(String userName);
}
