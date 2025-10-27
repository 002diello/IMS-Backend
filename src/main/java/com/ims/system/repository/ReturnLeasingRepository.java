package com.ims.system.repository;

import com.ims.system.entity.ReturnLeasing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReturnLeasingRepository extends JpaRepository<ReturnLeasing, Long> {
}