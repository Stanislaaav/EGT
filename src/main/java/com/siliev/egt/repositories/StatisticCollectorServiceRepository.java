package com.siliev.egt.repositories;

import com.siliev.egt.entities.StatisiticCollectorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticCollectorServiceRepository extends JpaRepository<StatisiticCollectorEntity, String> {

    //    //TODO not working
    @Query(value = "SELECT *\n"
        + "FROM public.statistic_collectors\n"
        + "WHERE \"timestamp\" >= NOW() - INTERVAL ?1\n"
        + "ORDER BY \"timestamp\" DESC", nativeQuery = true)
    List<StatisiticCollectorEntity> getHistory();
}
