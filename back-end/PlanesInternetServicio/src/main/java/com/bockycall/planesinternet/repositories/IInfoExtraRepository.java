package com.bockycall.planesinternet.repositories;

import com.bockycall.planesinternet.entities.InfoExtra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInfoExtraRepository extends JpaRepository<InfoExtra,Long> {
    List<InfoExtra> findByPlanInternetId(Long id);
    void deleteByPlanInternetId(Long plan_internet_id);
}
