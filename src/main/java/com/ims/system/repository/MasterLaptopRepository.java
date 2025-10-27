package com.ims.system.repository;

import com.ims.system.entity.MasterLaptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface MasterLaptopRepository extends JpaRepository<MasterLaptop, Long> {
    Optional<MasterLaptop> findByPcId(String pcId);
}