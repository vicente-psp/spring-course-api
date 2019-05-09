package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOwnerId(Long id);

    Page<Request> findAllByOwnerId(Long id, Pageable pageable);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE tb_request SET request_state = ?2 WHERE id = ?1")
    int updateStatus(Long id, String state);

}
