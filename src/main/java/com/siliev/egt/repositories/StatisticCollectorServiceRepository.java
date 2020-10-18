package com.siliev.egt.repositories;

import com.siliev.egt.entities.StatisiticCollectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticCollectorServiceRepository extends JpaRepository<StatisiticCollectorEntity, String> {

}
