package com.siliev.egt.repositories;

import com.siliev.egt.entities.LatestRateEntity;
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

}
