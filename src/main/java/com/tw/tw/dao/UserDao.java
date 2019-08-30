package com.tw.tw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findWithFollowingByUserId(Long id);
}
