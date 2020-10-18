package com.siliev.egt.repositories;

import com.siliev.egt.entities.LatestRateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LatestRateRepository extends JpaRepository<LatestRateEntity, Long> {

    @Query(value = ""
        + "SELECT pk_latest_rate, base, date, success, \"timestamp\" "
        + "FROM public.latest_rates "
        + "ORDER BY pk_latest_rate "
        + "DESC LIMIT 1", nativeQuery = true)
    LatestRateEntity findLatest();

    @Query(value = "SELECT pk_request_id, client, service_name, \"timestamp\" FROM public.unified_statistical_information WHERE \"timestamp\" >= NOW() - INTERVAL '1 HOURS' ORDER BY pk_request_id DESC", nativeQuery = true)
    List<LatestRateEntity> getHistory(String timeInterval);
}
