package com.ims.system.repository;

import com.ims.system.entity.AssignRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignRecordRepository extends JpaRepository<AssignRecord, Long> {
}
