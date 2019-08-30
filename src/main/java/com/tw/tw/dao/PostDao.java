package com.tw.tw.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    Collection<Post> findAllByUserId(List<Long> ids);

    Collection<Post> findAllByUserId(Long id, Sort sort);
}
